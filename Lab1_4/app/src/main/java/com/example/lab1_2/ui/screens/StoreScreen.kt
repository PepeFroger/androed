package com.example.lab1_2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab1_2.data.model.Product
import com.example.lab1_2.data.model.ProductCategory
import com.example.lab1_2.data.repository.ProductRepository
import com.example.lab1_2.ui.components.store.StoreBottomBar
import com.example.lab1_2.ui.viewmodels.StoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(
    onBackToHome: () -> Unit,
    onOpenProduct: (Product) -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenCart: () -> Unit,
    storeViewModel: StoreViewModel
) {
    var selectedCategory by remember { mutableStateOf(ProductCategory.PHONES) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Магазин") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            StoreBottomBar(
                cartCount = storeViewModel.cart.size,
                favoriteCount = storeViewModel.favorites.size,
                selectedTab = null,
                onHomeClick = onBackToHome,
                onFavoritesClick = onOpenFavorites,
                onCartClick = onOpenCart
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            PrimaryScrollableTabRow(
                selectedTabIndex = selectedCategory.ordinal,
                edgePadding = 8.dp
            ) {
                ProductCategory.entries.forEach { cat ->
                    Tab(
                        selected = selectedCategory == cat,
                        onClick = { selectedCategory = cat },
                        text = { Text(cat.title) }
                    )
                }
            }

            val products = remember(selectedCategory) {
                ProductRepository.productsFor(selectedCategory)
            }

            when (selectedCategory) {
                ProductCategory.PHONES -> PhoneListScreen(products, onOpenProduct)
                ProductCategory.LAPTOPS -> LaptopGridScreen(products, onOpenProduct)
                ProductCategory.AUDIO -> AudioCarouselScreen(products, onOpenProduct)
            }
        }
    }
}