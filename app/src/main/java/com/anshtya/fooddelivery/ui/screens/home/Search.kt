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
fun Search(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            FoodDeliveryBottomNavBar(
                currentRoute = HomeSections.SEARCH.route,
                onNavigateToRoute = onNavigateToRoute
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Button(onClick = { onFoodClick(3) }) {
                Text(text = "search")
            }
        }
    }
}