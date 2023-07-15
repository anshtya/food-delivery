package com.anshtya.fooddelivery.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.anshtya.fooddelivery.ui.screens.fooddetail.FoodDetail
import com.anshtya.fooddelivery.ui.screens.home.HomeSections
import com.anshtya.fooddelivery.ui.screens.home.homeGraph
import com.anshtya.fooddelivery.ui.theme.FoodDeliveryTheme

object Destinations {
    const val HOME_ROUTE = "home"
    const val FOOD_DETAIL_ROUTE = "food"
    const val FOOD_ID = "foodId"
}

@Composable
fun FoodDeliveryApp() {
    FoodDeliveryTheme {
        val navController: NavHostController = rememberNavController()
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = Destinations.HOME_ROUTE
            ) {
                navigation(
                    route = Destinations.HOME_ROUTE,
                    startDestination = HomeSections.FEED.route
                ) {
                    homeGraph(
                        onFoodClick = { id ->
                            navController.navigateToFoodDetail(id)
                        },
                        onNavigateToRoute = {
                            navController.navigateToBottomBarRoute(it)
                        }
                    )
                }
                composable(
                    route = "${Destinations.FOOD_DETAIL_ROUTE}/{${Destinations.FOOD_ID}}"
                ) { backStackEntry ->
                    FoodDetail(
                        foodId = backStackEntry.arguments?.getString(Destinations.FOOD_ID) ?: ""
                    )
                }
            }
        }
    }
}

fun NavController.navigateToBottomBarRoute(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id)
        launchSingleTop = true
    }
}

fun NavController.navigateToFoodDetail(id: Int) {
    navigate("${Destinations.FOOD_DETAIL_ROUTE}/${id}")
}