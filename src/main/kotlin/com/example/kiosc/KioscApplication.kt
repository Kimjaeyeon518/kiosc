package com.example.kiosc

import com.example.kiosc.domain.*
import com.example.kiosc.domain.enums.Option
import com.example.kiosc.domain.product.beer.Cass
import com.example.kiosc.domain.product.beer.Hite
import com.example.kiosc.domain.product.beer.Terra
import com.example.kiosc.domain.product.drink.Coke
import com.example.kiosc.domain.product.drink.Juice
import com.example.kiosc.domain.product.frozencustard.ChocoIceCream
import com.example.kiosc.domain.product.frozencustard.SoftIceCream
import com.example.kiosc.domain.menu.Beer
import com.example.kiosc.domain.menu.Burger
import com.example.kiosc.domain.menu.Drink
import com.example.kiosc.domain.menu.FrozenCustard
import com.example.kiosc.domain.product.burger.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KioscApplication

val menuList = buildMenu()
val productList = buildProduct()
val cartMap = mutableMapOf<String, Int>()
val cartList = mutableListOf<Product>()
var totalSale: Double = 0.0
var totalSaleProductMap = mutableMapOf<String, Int>()

fun main(args: Array<String>) {
    runApplication<KioscApplication>(*args)
    printMainMenu()
}

fun printMainMenu() {
    println()
    println("------------------------------------------------------")
    println("SHAKESHACK BURGER 에 오신걸 환영합니다.")
    println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
    println()
    println("[ SHAKESHACK MENU ]")

    menuList.forEachIndexed{ index, menu ->
        println("${index+1}. ${menu.print()}")
    }

    println()
    println("[ ORDER MENU ]")
    println("5. Order       | 장바구니를 확인 후 주문합니다.")
    println("6. Cancel      | 진행중인 주문을 취소합니다.")
    println("------------------------------------------------------")

    when(readln()) {
        "1" -> printProductMenu(Burger())
        "2" -> printProductMenu(FrozenCustard())
        "3" -> printProductMenu(Drink())
        "4" -> printProductMenu(Beer())
        "5" -> printOrderMenu()
        "6" -> printOrderCancelMenu()
        "0" -> printTotalSale()
        else -> {
            printError()
            printMainMenu()
        }
    }
}

fun printTotalSale() {
    println("[ 총 판매금액 현황 ]")
    println("현재까지 총 판매된 금액은 [ W ${totalSale} ] 입니다.")
    println()
    println("[ 총 판매상품 목록 현황 ]")
    println("현재까지 총 판매된 상품 목록은 아래와 같습니다.")
    totalSaleProductMap.entries.forEach {
        println("- ${it.key}  |  ${it.value} 개")
    }

    println()
    println("1. 돌아가기")
    when(readln()) {
        "1" -> printMainMenu()
        else -> {
            printError()
            printTotalSale()
        }
    }
}

fun printOrderCancelMenu() {
    println("진행하던 주문을 취소하시겠습니까?")
    println("1. 확인        2. 취소")
    when (readln()) {
        "1" -> {
            cartMap.clear()
            cartList.clear()
            println("진행하던 주문이 취소되었습니다.")
            println()
            printMainMenu()
        }
        "2" -> {
            println()
            printMainMenu()
        }
        else -> {
            printError()
            printOrderCancelMenu()
        }
    }
}

fun printError() {
    println("유효하지 않은 메뉴 번호입니다. 다시 입력해주세요 !")
    println()
}

fun printOrderMenu() {
    if (cartMap.isEmpty()) {
        println("주문할 상품이 담겨있지 않습니다. 장바구니에 상품을 먼저 담아주세요 !")
    } else {
        println("아래와 같이 주문 하시겠습니까?")
        println("[ Orders ]")
        cartMap.entries.forEach { println("${it.key} | ${it.value} 개")}
        println()
        println("[ Total ]")
        println("₩ ${cartList.sumOf { it.getPrice() }}")
        println()
        println("1. 주문      2. 메뉴판")
        orderConfirmed()
    }
    printMainMenu()
}

fun orderConfirmed() {
    when (readln()) {
        "1" -> {
            println("주문이 완료되었습니다!")
            println()
            println("대기번호는 [ 1 ] 번 입니다.")
            println("(3초후 메뉴판으로 돌아갑니다.)")
            totalSale += cartList.sumOf { it.getPrice() }
            cartMap.entries.forEach {
                totalSaleProductMap[it.key] = totalSaleProductMap.getOrDefault(it.key, 0) + it.value
            }
            cartMap.clear()
            cartList.clear()
            Thread.sleep(3000)
        }
        "2" -> {}
        else -> {
            printError()
            printOrderMenu()
        }
    }
}

fun printProductMenu(menu: Menu) {
    val menuClass = menu::class.java
    println("[ ${menu.getName()} MENU ]")
    val filteredProductList = productList.filter { product -> product.javaClass.superclass == menuClass }
    filteredProductList.forEachIndexed { index, product ->
        println("${index+1}. ${product.print()}")
    }

    val num = readln()
    println()

    if (num.toInt() > filteredProductList.size) {
        printError()
        printProductMenu(menu)
    } else {
        val product = filteredProductList[num.toInt() - 1]
        if (product.hasOption()) {
            printOptionMenu(product, menu)
        } else {
            printAddCartMenu(product, menu)
        }
    }
}

fun printOptionMenu(product: Product, menu: Menu) {
    println(product.print())
    println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?")
    product.printOption()

    var optionProduct: Product = product.clone()
    when(readln()) {
        "1" -> {
            optionProduct.applyOption(Option.SINGLE)
        }
        "2" -> {
            optionProduct.applyOption(Option.DOUBLE)
        }
        else -> {
            printError()
            printOptionMenu(product, menu)
        }
    }
    printAddCartMenu(optionProduct, menu)
}

fun printAddCartMenu(product: Product, menu: Menu) {
    println(product.print())
    println("위 메뉴를 장바구니에 추가하시겠습니까?")
    println("1. 확인        2. 취소")
    when(readln()) {
        "1" -> {
            cartMap[product.print()] = cartMap.getOrDefault(product.print(), 0) + 1
            cartList.add(product)
            println()
            println("${product.getName()} 가 장바구니에 추가되었습니다.")
            println()
            printMainMenu()
        }
        "2" -> {
            println()
            println("장바구니 추가를 취소합니다.")
            println()
            printProductMenu(menu)
        }
        else -> {
            printError()
            printAddCartMenu(product, menu)
        }
    }
}

fun buildMenu(): List<Menu> = mutableListOf(Burger(), FrozenCustard(), Drink(), Beer())
fun buildProduct(): List<Product> =
    mutableListOf(
        CheeseBurger(), Hamburger(), ShackBurger(), ShroomBurger(), SmokeShack(),
        SoftIceCream(), ChocoIceCream(),
        Coke(), Juice(),
        Cass(), Hite(), Terra()
    )

