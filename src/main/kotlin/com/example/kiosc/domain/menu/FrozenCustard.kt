package com.example.kiosc.domain.menu

import com.example.kiosc.domain.Menu
import com.example.kiosc.domain.enums.Option

open class FrozenCustard (
    private val name: String = "Frozen Custard",
    private val description: String = "매장에서 신선하게 만드는 아이스크림"
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