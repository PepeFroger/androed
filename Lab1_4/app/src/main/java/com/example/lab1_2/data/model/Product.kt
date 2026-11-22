// model/Product.kt
package com.example.lab1_2.data.model

import androidx.annotation.DrawableRes
import com.example.lab1_2.R

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val oldPrice: String? = null,
    @DrawableRes val imageRes: Int,
    val category: ProductCategory
)

enum class ProductCategory(val title: String) {
    PHONES("Телефоны"),
    LAPTOPS("Ноутбуки"),
    AUDIO("Аудио")
}