package com.example.lab1_2.model

import androidx.annotation.DrawableRes
import com.example.lab1_2.R

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val oldPrice: String?,
    @DrawableRes val imageRes: Int
)

val sampleCategories = listOf(
    "Телефоны", "Ноутбуки", "ТВ",
    "Аудио", "Игры", "Все"
)

val sampleProduct = Product(
    id = 1,
    name = "Смартфон Galaxy X",
    description = "6.7\", 256 ГБ, камера 108 МП",
    price = "59 990 ₽",
    oldPrice = "69 990 ₽",
    imageRes = R.drawable.photo
)