package com.example.lab1_2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab1_2.data.model.ProductCategory
import com.example.lab1_2.data.repository.ProductRepository
import com.example.lab1_2.ui.components.store.StoreBottomBar
import com.example.lab1_2.ui.components.store.StoreTab
import com.example.lab1_2.ui.screens.*
import com.example.lab1_2.ui.viewmodels.StoreViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val storeViewModel: StoreViewModel = viewModel()

    val goHome: () -> Unit = {
        navController.popBackStack(Screen.Home.route, false)
    }
    val goFavorites: () -> Unit = {
        navController.navigate(Screen.Favorites.route) {
            popUpTo(Screen.Store.route) { inclusive = false }
            launchSingleTop = true
        }
    }
    val goCart: () -> Unit = {
        navController.navigate(Screen.Cart.route) {
            popUpTo(Screen.Store.route) { inclusive = false }
            launchSingleTop = true
        }
    }

    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                onOpenQuiz = { navController.navigate(Screen.Quiz.route) },
                onOpenStore = { navController.navigate(Screen.Store.route) }
            )
        }

        composable(Screen.Store.route) {
            StoreScreen(
                onBackToHome = goHome,
                onOpenProduct = { p -> navController.navigate(Screen.ProductDetail.createRoute(p.id)) },
                onOpenFavorites = goFavorites,
                onOpenCart = goCart,
                storeViewModel = storeViewModel
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            val product = ProductRepository.productsFor(ProductCategory.PHONES).firstOrNull { it.id == productId }
                ?: ProductRepository.productsFor(ProductCategory.LAPTOPS).firstOrNull { it.id == productId }
                ?: ProductRepository.productsFor(ProductCategory.AUDIO).firstOrNull { it.id == productId }
                ?: return@composable

            ProductDetailScreen(
                product = product,
                isInCart = storeViewModel.isInCart(product.id),
                isFavorite = storeViewModel.isFavorite(product.id),
                onAddToCart = { storeViewModel.addToCart(product) },
                onRemoveFromCart = { storeViewModel.removeFromCart(product.id) },
                onAddToFavorites = { storeViewModel.addToFavorites(product) },
                onRemoveFromFavorites = { storeViewModel.removeFromFavorites(product.id) },
                onBack = { navController.popBackStack() },
                bottomBar = {
                    StoreBottomBar(
                        cartCount = storeViewModel.cart.size,
                        favoriteCount = storeViewModel.favorites.size,
                        selectedTab = null,
                        onHomeClick = goHome,
                        onFavoritesClick = goFavorites,
                        onCartClick = goCart
                    )
                }
            )
        }

        composable(Screen.Quiz.route) {
            QuizScreen(
                onBackToHome = { navController.popBackStack(Screen.Home.route, false) }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                products = storeViewModel.favorites.values.toList(),
                onOpenProduct = { p -> navController.navigate(Screen.ProductDetail.createRoute(p.id)) },
                onRemoveFromFavorites = { p -> storeViewModel.removeFromFavorites(p.id) },
                onBack = { navController.popBackStack() },
                bottomBar = {
                    StoreBottomBar(
                        cartCount = storeViewModel.cart.size,
                        favoriteCount = storeViewModel.favorites.size,
                        selectedTab = StoreTab.FAVORITES,
                        onHomeClick = goHome,
                        onFavoritesClick = goFavorites,
                        onCartClick = goCart
                    )
                }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                products = storeViewModel.cart.values.toList(),
                onOpenProduct = { p -> navController.navigate(Screen.ProductDetail.createRoute(p.id)) },
                onRemoveFromCart = { p -> storeViewModel.removeFromCart(p.id) },
                onBack = { navController.popBackStack() },
                bottomBar = {
                    StoreBottomBar(
                        cartCount = storeViewModel.cart.size,
                        favoriteCount = storeViewModel.favorites.size,
                        selectedTab = StoreTab.CART,
                        onHomeClick = goHome,
                        onFavoritesClick = goFavorites,
                        onCartClick = goCart
                    )
                }
            )
        }
    }
}