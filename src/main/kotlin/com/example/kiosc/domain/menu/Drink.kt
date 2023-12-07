package com.example.kiosc.domain.menu

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.enums.Option

open class Drink (
    private val name: String = "Drink",
    private val description: String = "매장에서 직접 만드는 음료"
): Menu {
    override fun print(): String {
        return "$name   |   $description"
    }
    override fun getOptions(): List<Option> {
//        return listOf(Option.SINGLE, Option.DOUBLE)
        return listOf()
    }
    override fun getName(): String {
        return name
    }
}