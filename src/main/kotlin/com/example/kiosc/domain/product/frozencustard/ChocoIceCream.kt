package com.example.kiosc.domain.product.frozencustard

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.Product
import com.example.kiosc.domain.enums.Option
import com.example.kiosc.domain.menu.FrozenCustard

class ChocoIceCream(
    private var name: String = "Chocolate IceCream",
    private val description: String = "초콜릿 소프트 아이스크림",
    private var price: Double = 2.5
): Product, FrozenCustard() {
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
        return ChocoIceCream(name, description, price)
    }
}