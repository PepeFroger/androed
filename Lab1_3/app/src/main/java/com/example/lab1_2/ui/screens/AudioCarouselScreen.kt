package com.example.lab1_2.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import androidx.compose.ui.unit.dp
import com.example.lab1_2.data.model.Product
import com.example.lab1_2.ui.components.store.ProductThumbnail
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AudioCarouselScreen(
    products: List<Product>,
    onOpenProduct: (Product) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { products.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 48.dp),
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val scale = lerp(
            start = 1f,
            stop = 0.8f,
            fraction = pageOffset.absoluteValue.coerceIn(0f, 1f)
        )
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            onClick = { onOpenProduct(products[page]) }
        ) {
            Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                ProductThumbnail(
                    products[page].imageRes,
                    Modifier.fillMaxWidth().height(220.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    products[page].name, style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    products[page].price, style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}