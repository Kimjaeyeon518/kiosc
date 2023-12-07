package com.example.kiosc.domain.product.burger

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.Product
import com.example.kiosc.domain.enums.Option
import com.example.kiosc.domain.menu.Burger

class SmokeShack(
    private var name: String = "SmokeShack",
    private val description: String = "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
    private var price: Double = 8.9
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
        return SmokeShack(name, description, price)
    }
}