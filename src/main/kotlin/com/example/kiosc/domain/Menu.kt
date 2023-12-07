package com.example.kiosc.domain

import com.example.kiosc.domain.enums.Option

interface Menu {
    fun print(): String
    fun getOptions(): List<Option>
    fun getName(): String
}