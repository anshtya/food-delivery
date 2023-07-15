package com.anshtya.fooddelivery.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anshtya.fooddelivery.R

enum class HomeSections(
    @StringRes val title: Int,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, Icons.Rounded.Home, "home/feed"),
    SEARCH(R.string.home_search, Icons.Outlined.Search, Icons.Rounded.Search, "home/search"),
    CART(R.string.home_cart, Icons.Outlined.ShoppingCart, Icons.Rounded.ShoppingCart, "home/cart"),
}

fun NavGraphBuilder.homeGraph(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit
) {
    composable(route = HomeSections.FEED.route) {
        Feed(
            onFoodClick = onFoodClick,
            onNavigateToRoute = onNavigateToRoute
        )
    }
    composable(route = HomeSections.SEARCH.route) {
        Search(
            onFoodClick = onFoodClick,
            onNavigateToRoute = onNavigateToRoute
        )
    }
    composable(route = HomeSections.CART.route) {
        Cart(
            onFoodClick = onFoodClick,
            onNavigateToRoute = onNavigateToRoute
        )
    }
}

