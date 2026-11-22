package com.example.lab1_2.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Store : Screen("store")
    data object Quiz : Screen("quiz")
    data object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId"
    }
    data object Favorites : Screen("favorites")
    data object Cart : Screen("cart")
}