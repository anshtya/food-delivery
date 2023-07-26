package com.anshtya.fooddelivery.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.screens.home.feed.Feed
import com.anshtya.fooddelivery.ui.screens.home.search.Search

enum class HomeSections(
    @StringRes val title: Int,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, Icons.Rounded.Home, "home/feed"),
    SEARCH(R.string.home_search, Icons.Outlined.Search, Icons.Rounded.Search, "home/search")
}

fun NavGraphBuilder.homeGraph(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    onNavigateToCart: () -> Unit,
) {
    composable(route = HomeSections.FEED.route) {
        Feed(
            onFoodClick = onFoodClick,
            onNavigateToRoute = onNavigateToRoute,
            onNavigateToCart = onNavigateToCart
        )
    }
    composable(route = HomeSections.SEARCH.route) {
        Search(
            onFoodClick = onFoodClick,
            onNavigateToRoute = onNavigateToRoute
        )
    }
}

