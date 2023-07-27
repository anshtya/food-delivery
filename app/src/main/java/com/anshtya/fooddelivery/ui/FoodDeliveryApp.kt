package com.anshtya.fooddelivery.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anshtya.fooddelivery.ui.screens.fooddetail.FoodDetail
import com.anshtya.fooddelivery.ui.screens.cart.Cart
import com.anshtya.fooddelivery.ui.screens.home.Home
import com.anshtya.fooddelivery.ui.theme.FoodDeliveryTheme

object Destinations {
    const val HOME_ROUTE = "home"
    const val FOOD_DETAIL_ROUTE = "food"
    const val FOOD_ID = "foodId"
    const val CART_ROUTE = "cart"
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
                composable(
                    route = Destinations.HOME_ROUTE,
                ) {
                    Home(
                        onFoodClick = { navController.navigateToFoodDetail(it) },
                        onCartClick = { navController.navigate(Destinations.CART_ROUTE) }
                    )
                }
                composable(
                    route = "${Destinations.FOOD_DETAIL_ROUTE}/{${Destinations.FOOD_ID}}"
                ) { backStackEntry ->
                    FoodDetail(
                        foodId = backStackEntry.arguments?.getString(Destinations.FOOD_ID) ?: ""
                    )
                }
                composable(
                    route = Destinations.CART_ROUTE
                ) {
                    Cart(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

fun NavHostController.navigateToFoodDetail(id: Int) {
    navigate("${Destinations.FOOD_DETAIL_ROUTE}/${id}")
}