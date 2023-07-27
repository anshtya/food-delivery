package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.anshtya.fooddelivery.ui.screens.home.HomeSections

@Composable
fun FoodDeliveryBottomNavBar(
    currentDestination: NavDestination?,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember { HomeSections.values() }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        items.forEach { item ->
            FoodDeliveryNavigationItem(
                item = item,
                selected = currentDestination?.route == item.route,
                onNavigateToRoute = { onNavigateToRoute(item.route) }
            )
        }
    }
}


