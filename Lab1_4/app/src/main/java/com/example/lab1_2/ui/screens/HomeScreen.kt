package com.example.lab1_2.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab1_2.R
import com.example.lab1_2.ui.components.common.PromoBanner
import com.example.lab1_2.ui.components.common.SearchBar
import com.example.lab1_2.ui.components.common.ShopHeader
import com.example.lab1_2.ui.components.home.ActionButtons
import com.example.lab1_2.ui.components.home.CategorySection
import com.example.lab1_2.ui.components.home.ProductCard
import com.example.lab1_2.ui.viewmodels.HomeViewModel


private fun shareApp(context: android.content.Context, text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

@Composable
fun HomeScreen(
    onOpenQuiz: () -> Unit,
    onOpenStore: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        ShopHeader(
            shopName = stringResource(R.string.shop_name),
            onCartClick = { }
        )

        Text(
            text = stringResource(R.string.shop_slogan),
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 4.dp)
        )

        SearchBar(
            query = viewModel.searchText,
            onQueryChange = viewModel::onSearchChange,
            hint = stringResource(R.string.hint_search),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )

        CategorySection(
            categories = viewModel.categories,
            onCategoryClick = { },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        PromoBanner(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        )

        ProductCard(
            product = viewModel.featuredProduct,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
        )

        val shareText = stringResource(R.string.share_text)
        ActionButtons(
            buyText = stringResource(R.string.btn_buy),
            cartText = stringResource(R.string.btn_cart),
            quizText = stringResource(R.string.btn_quiz),
            onBuyClick = onOpenStore,
            onCartClick = { shareApp(context, shareText) },
            onQuizClick = onOpenQuiz,
        )
    }
}