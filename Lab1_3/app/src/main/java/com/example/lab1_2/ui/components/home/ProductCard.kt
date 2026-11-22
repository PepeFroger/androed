package com.example.lab1_2.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        ProductImage(imageRes = product.imageRes)
        Spacer(Modifier.width(12.dp))
        ProductInfo(
            name = product.name,
            description = product.description,
            price = product.price,
            oldPrice = product.oldPrice,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ProductImage(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(120.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
    )
}

@Composable
private fun ProductInfo(
    name: String,
    description: String,
    price: String,
    oldPrice: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(Modifier.height(4.dp))
        Text(description, color = Color.Gray, fontSize = 12.sp)
        Spacer(Modifier.height(8.dp))
        PriceRow(price = price, oldPrice = oldPrice)
    }
}

@Composable
private fun PriceRow(
    price: String,
    oldPrice: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = price,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (oldPrice != null) {
            Spacer(Modifier.width(8.dp))
            Text(
                text = oldPrice,
                color = Color.Gray,
                fontSize = 14.sp,
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}