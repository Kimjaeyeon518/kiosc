package com.example.kiosc.domain.product.beer

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.Product
import com.example.kiosc.domain.enums.Option
import com.example.kiosc.domain.menu.Beer
import com.example.kiosc.domain.menu.Drink

class Hite(
    private var name: String = "Hite",
    private val description: String = "하이트",
    private var price: Double = 4.0
): Product, Beer() {
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
        return Hite(name, description, price)
    }
}