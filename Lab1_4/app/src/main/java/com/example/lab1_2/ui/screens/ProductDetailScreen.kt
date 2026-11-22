// ui/screens/ProductDetailScreen.kt
package com.example.lab1_2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1_2.data.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    isInCart: Boolean,
    isFavorite: Boolean,
    onAddToCart: () -> Unit,
    onRemoveFromCart: () -> Unit,
    onAddToFavorites: () -> Unit,
    onRemoveFromFavorites: () -> Unit,
    onBack: () -> Unit,
    bottomBar: @Composable () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Назад")
                    }
                }
            )
        },
        bottomBar = bottomBar
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)), // Белый фон со скруглениями
                contentAlignment = Alignment.Center // Центрируем картинку
            ) {
                Image(
                    painter = painterResource(product.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    // Добавляем padding, чтобы картинка не прилипала к краям белого квадрата
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    product.price,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                product.oldPrice?.let {
                    Spacer(Modifier.width(8.dp))
                    Text(it, fontSize = 16.sp, textDecoration = TextDecoration.LineThrough)
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(product.description, fontSize = 16.sp)
            Spacer(Modifier.height(24.dp))

            Button(
                onClick = if (isInCart) onRemoveFromCart else onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                colors = if (isInCart)
                    ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                else
                    ButtonDefaults.buttonColors()
            ) {
                Text(if (isInCart) "Удалить из корзины" else "В корзину")
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = if (isFavorite) onRemoveFromFavorites else onAddToFavorites,
                modifier = Modifier.fillMaxWidth(),
                colors = if (isFavorite)
                    ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                else
                    ButtonDefaults.outlinedButtonColors()
            ) {
                Text(if (isFavorite) "Удалить из избранного" else "В избранное")
            }
        }
    }
}