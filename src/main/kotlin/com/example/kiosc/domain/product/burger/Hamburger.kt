package com.example.kiosc.domain.product.burger

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.menu.Burger
import com.example.kiosc.domain.Product
import com.example.kiosc.domain.enums.Option

class Hamburger(
    private var name: String = "Hamburger",
    private val description: String = "비프패티를 기반으로 야채가 들어간 기본버거",
    private var price: Double = 5.4
): Product, Burger() {
    override fun print(): String {
        return "$name   |   ₩ ${price}    |   $description"
    }

    override fun hasOption(): Boolean {
        return super.getOptions().isNotEmpty()
    }

    override fun getName(): String {
        return name
    }

    override fun getPrice(): Double {
        return price
    }

    override fun printOption() {
        super.getOptions().forEachIndexed{ index, op ->
            run {
                println("${index + 1}. ${op.name}(₩ ${this.price + index})")
            }
        }
    }

    override fun applyOption(option: Option) {
        this.name += " (${option.name})"
        if (option == Option.DOUBLE) {
            this.price += 1
        }
    }

    override fun clone(): Product {
        return Hamburger(name, description, price)
    }
}