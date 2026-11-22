package com.example.lab1_2.data.repository

import com.example.lab1_2.R
import com.example.lab1_2.data.model.Product
import com.example.lab1_2.data.model.ProductCategory

object ProductRepository {

    private val phones = listOf(
        Product(1, "Galaxy X", "6.7\", 256 ГБ, 108 МП", "59 990 ₽", "69 990 ₽", R.drawable.photo, ProductCategory.PHONES),
        Product(2, "iPhone 15", "6.1\", 128 ГБ, A16", "79 990 ₽", null, R.drawable.photo, ProductCategory.PHONES),
        Product(3, "Pixel 8", "6.2\", 128 ГБ, 50 МП", "54 990 ₽", "59 990 ₽", R.drawable.photo, ProductCategory.PHONES),
        Product(4, "Xiaomi 14", "6.36\", 256 ГБ, 50 МП", "49 990 ₽", null, R.drawable.photo, ProductCategory.PHONES)
    )

    private val laptops = listOf(
        Product(101, "MacBook Air", "13.6\", M2, 8/256", "99 990 ₽", null, R.drawable.photo, ProductCategory.LAPTOPS),
        Product(102, "Lenovo IdeaPad", "15.6\", i5, 16/512", "54 990 ₽", "64 990 ₽", R.drawable.photo, ProductCategory.LAPTOPS),
        Product(103, "ASUS ZenBook", "14\", Ryzen 7, 16/1TB", "74 990 ₽", null, R.drawable.photo, ProductCategory.LAPTOPS),
        Product(104, "HP Pavilion", "15.6\", i7, 16/512", "69 990 ₽", "79 990 ₽", R.drawable.photo, ProductCategory.LAPTOPS)
    )

    private val audio = listOf(
        Product(201, "AirPods Pro", "ANC, USB-C", "24 990 ₽", "29 990 ₽", R.drawable.photo, ProductCategory.AUDIO),
        Product(202, "Sony WH-1000XM5", "Over-ear, ANC", "34 990 ₽", null, R.drawable.photo, ProductCategory.AUDIO),
        Product(203, "JBL Flip 6", "Портативная колонка", "12 990 ₽", "14 990 ₽", R.drawable.photo, ProductCategory.AUDIO),
        Product(204, "Bose QuietComfort", "Over-ear, ANC", "39 990 ₽", null, R.drawable.photo, ProductCategory.AUDIO)
    )

    fun productsFor(category: ProductCategory): List<Product> {
        val base = when (category) {
            ProductCategory.PHONES -> phones
            ProductCategory.LAPTOPS -> laptops
            ProductCategory.AUDIO -> audio
        }
        return buildList {
            base.forEach { add(it) }
            (1..2).forEach { copy ->
                base.forEach { p -> add(p.copy(id = p.id + copy * 1000)) }
            }
        }
    }

    val featuredProduct: Product = Product(
        id = 1,
        name = "Смартфон Galaxy X",
        description = "6.7\", 256 ГБ, камера 108 МП",
        price = "59 990 ₽",
        oldPrice = "69 990 ₽",
        imageRes = R.drawable.photo,
        category = ProductCategory.PHONES
    )

    val homeCategories: List<String> = listOf(
        "Телефоны", "Ноутбуки", "ТВ", "Аудио", "Игры", "Все"
    )
}