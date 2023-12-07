package com.example.kiosc.domain

import com.example.kiosc.domain.enums.Option

interface Product {
    fun print(): String
    fun hasOption(): Boolean
    fun getName(): String
    fun getPrice(): Double
    fun printOption()
    fun applyOption(option: Option)
    fun clone(): Product
}