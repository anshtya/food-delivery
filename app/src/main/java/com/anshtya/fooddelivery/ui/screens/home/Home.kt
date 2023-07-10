package com.anshtya.fooddelivery.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anshtya.fooddelivery.R

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, "home/feed"),
    SEARCH(R.string.home_search, Icons.Outlined.Search, "home/search"),
    CART(R.string.home_cart, Icons.Outlined.ShoppingCart, "home/cart"),
}

fun NavGraphBuilder.homeGraph() {
    composable(route = HomeSections.FEED.route) {
        Feed()
    }
    composable(route = HomeSections.SEARCH.route) {
        Search()
    }
    composable(route = HomeSections.CART.route) {
        Cart()
    }
}

