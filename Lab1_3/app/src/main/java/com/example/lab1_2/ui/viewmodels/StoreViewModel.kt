package com.example.lab1_2.ui.viewmodels

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.lab1_2.data.model.Product

class StoreViewModel : ViewModel() {

    val cart = mutableStateMapOf<Int, Product>()
    val favorites = mutableStateMapOf<Int, Product>()

    fun addToCart(p: Product) { cart[p.id] = p }
    fun addToFavorites(p: Product) { favorites[p.id] = p }

    fun isInCart(id: Int): Boolean = cart.containsKey(id)
    fun isFavorite(id: Int): Boolean = favorites.containsKey(id)

    fun removeFromCart(id: Int) { cart.remove(id) }
    fun removeFromFavorites(id: Int) { favorites.remove(id) }
}