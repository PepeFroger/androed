package com.example.lab1_2.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lab1_2.data.model.Product
import com.example.lab1_2.data.repository.ProductRepository

class HomeViewModel : ViewModel() {

    var searchText by mutableStateOf("")
        private set

    val categories: List<String> = ProductRepository.homeCategories
    val featuredProduct: Product = ProductRepository.featuredProduct

    fun onSearchChange(newValue: String) {
        searchText = newValue
    }
}