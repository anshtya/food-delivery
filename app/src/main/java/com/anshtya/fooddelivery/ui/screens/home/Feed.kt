package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.data.Repository
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodItem
import com.anshtya.fooddelivery.ui.components.LocationBar

@Composable
fun Feed(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendedFoodItems = remember { Repository.getRecommendedList() }

    Scaffold(
        bottomBar = {
            FoodDeliveryBottomNavBar(
                currentRoute = HomeSections.FEED.route,
                onNavigateToRoute = onNavigateToRoute
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier.padding(innerPadding)
        ) {
            item {
                LocationBar()
            }
            item {
                Text(
                    text = stringResource(R.string.recommended),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(
                items = recommendedFoodItems,
            ) { item ->
                FoodItem(
                    item = item,
                    onFoodClick = onFoodClick
                )
            }
        }
    }
}