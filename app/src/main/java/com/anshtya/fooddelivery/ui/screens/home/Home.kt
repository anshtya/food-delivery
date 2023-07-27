package com.anshtya.fooddelivery.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.Destinations
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySnackbar
import com.anshtya.fooddelivery.ui.screens.home.feed.Feed
import com.anshtya.fooddelivery.ui.screens.home.search.Search
import com.anshtya.fooddelivery.ui.util.SnackbarData

enum class HomeSections(
    @StringRes val title: Int,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, Icons.Rounded.Home, "home/feed"),
    SEARCH(R.string.home_search, Icons.Outlined.Search, Icons.Rounded.Search, "home/search")
}

@Composable
fun Home(
    onFoodClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val cartNotEmpty by homeViewModel.cartNotEmpty.collectAsStateWithLifecycle()
    val totalItems by homeViewModel.totalItems.collectAsStateWithLifecycle()
    val totalPrice by homeViewModel.totalPrice.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            FoodDeliveryBottomNavBar(
                currentDestination = navBackStackEntry?.destination,
                onNavigateToRoute = { navController.navigateToBottomBarRoute(it) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeNavGraph(
                navController = navController,
                onFoodClick = onFoodClick
            )

            AnimatedVisibility(
                visible = cartNotEmpty,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 }
                ),
                exit = slideOutVertically(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                FoodDeliverySnackbar(
                    snackbarData = SnackbarData(totalItems, totalPrice),
                    onClick = onCartClick
                )
            }
        }
    }
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    onFoodClick: (Int) -> Unit
) {
    NavHost(
        navController = navController,
        route = Destinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        composable(
            route = HomeSections.FEED.route
        ) {
            Feed(
                onFoodClick = onFoodClick
            )
        }
        composable(
            route = HomeSections.SEARCH.route
        ) {
            Search(
                onFoodClick = onFoodClick
            )
        }
    }
}

fun NavHostController.navigateToBottomBarRoute(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id)
        launchSingleTop = true
    }
}