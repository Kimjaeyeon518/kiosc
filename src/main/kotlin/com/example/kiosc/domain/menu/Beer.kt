package com.example.kiosc.domain.menu

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.enums.Option

open class Beer (
    private val name: String = "Beer",
    private val description: String = "뉴욕 브루클린 브루어리에서 양조한 맥주"
): Menu {
    override fun print(): String {
        return "$name   |   $description"
    }
    override fun getOptions(): List<Option> {
        return listOf()
    }

    override fun getName(): String {
        return name
    }
}