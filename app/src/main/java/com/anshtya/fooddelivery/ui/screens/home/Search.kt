package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySearchBar
import kotlinx.coroutines.delay

@Composable
fun Search(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            FoodDeliveryBottomNavBar(
                currentRoute = HomeSections.SEARCH.route,
                onNavigateToRoute = onNavigateToRoute
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FoodDeliverySearchBar(
                text = text,
                isSearching = isSearching,
                onValueChange = { text = it },
                onClearText = { text = "" },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
            )

            LaunchedEffect(text) {
                isSearching = true
                delay(1000L)
                isSearching = false
            }
        }
    }
}