package com.example.lab1_2.ui.components.store

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

enum class StoreTab { HOME, FAVORITES, CART }

@Composable
fun StoreBottomBar(
    cartCount: Int,
    favoriteCount: Int,
    selectedTab: StoreTab? = null,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onCartClick: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == StoreTab.HOME,
            onClick = onHomeClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Главная") },
            label = { Text("Главная", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == StoreTab.FAVORITES,
            onClick = onFavoritesClick,
            icon = {
                BadgedBox(badge = {
                    if (favoriteCount > 0) Badge { Text(favoriteCount.toString()) }
                }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Избранное")
                }
            },
            label = { Text("Избранное", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == StoreTab.CART,
            onClick = onCartClick,
            icon = {
                BadgedBox(badge = {
                    if (cartCount > 0) Badge { Text(cartCount.toString()) }
                }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина")
                }
            },
            label = { Text("Корзина", fontSize = 11.sp) }
        )
    }
}