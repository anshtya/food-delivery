package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.ui.screens.home.HomeSections

@Composable
fun FoodDeliveryBottomNavBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        val items = HomeSections.values()
        items.forEach { item ->
            FoodDeliveryNavigationItem(
                item = item,
                currentDestination = currentRoute,
                onItemClick = { onItemClick(item.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    FoodDeliveryBottomNavBar(
        currentRoute = HomeSections.CART.route,
        onItemClick = {}
    )
}


