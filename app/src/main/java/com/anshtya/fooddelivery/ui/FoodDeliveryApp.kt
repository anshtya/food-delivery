package com.anshtya.fooddelivery.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
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
        Scaffold(
            bottomBar = {
                FoodDeliveryBottomNavBar()
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Destinations.HOME_ROUTE,
                modifier = Modifier.padding(innerPadding)
            ) {
                navigation(
                    route = Destinations.HOME_ROUTE,
                    startDestination = HomeSections.FEED.route
                ) {
                    homeGraph()
                }
                composable(
                    route = "${Destinations.FOOD_DETAIL_ROUTE}/{${Destinations.FOOD_ID}}"
                ) { backStackEntry ->
                    FoodDetail(
                        foodId = backStackEntry.arguments?.getInt(Destinations.FOOD_ID) ?: 0
                    )
                }
            }
        }
    }
}