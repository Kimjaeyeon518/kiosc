package com.example.kiosc.domain.menu

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.enums.Option

open class Burger (
    private val name: String = "Burger",
    private val description: String = "앵거스 비프 통살을 다져만든 버거"
): Menu {
    override fun print(): String {
        return "$name   |   $description"
    }

    override fun getOptions(): List<Option> {
        return listOf(Option.SINGLE, Option.DOUBLE)
    }

    override fun getName(): String {
        return name
    }
}