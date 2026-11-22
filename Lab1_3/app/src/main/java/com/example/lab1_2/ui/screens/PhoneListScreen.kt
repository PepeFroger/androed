package com.example.lab1_2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab1_2.data.model.Product
import com.example.lab1_2.ui.components.store.AdCard
import com.example.lab1_2.ui.components.store.ProductListCard

@Composable
fun PhoneListScreen(
    products: List<Product>,
    onOpenProduct: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        products.chunked(4).forEachIndexed { chunkIndex, chunk ->
            if (chunkIndex > 0) {
                item(key = "ad_$chunkIndex") { AdCard() }
            }
            items(items = chunk, key = { it.id }) { product ->
                ProductListCard(
                    product = product,
                    onClick = { onOpenProduct(product) }
                )
            }
        }
    }
}