package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar

@Composable
fun Cart(
    onFoodClick: (Int) -> Unit,
    onItemClick: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            FoodDeliveryBottomNavBar(
                currentRoute = HomeSections.CART.route,
                onItemClick = onItemClick
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Button(onClick = { onFoodClick(1) }) {
                Text(text = "cart")
            }
        }
    }
}