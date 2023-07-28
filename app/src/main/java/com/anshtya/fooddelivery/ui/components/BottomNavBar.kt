package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.anshtya.fooddelivery.ui.screens.home.HomeSections
import com.anshtya.fooddelivery.ui.util.noRippleClickable

@Composable
fun FoodDeliveryBottomNavBar(
    currentDestination: NavDestination?,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember { HomeSections.values() }
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
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
}

@Composable
fun FoodDeliveryNavigationItem(
    item: HomeSections,
    selected: Boolean,
    onNavigateToRoute: () -> Unit
) {
    if (selected) {
        Icon(
            imageVector = item.selectedIcon,
            contentDescription = stringResource(item.title),
            modifier = Modifier
                .size(30.dp)
                .noRippleClickable { onNavigateToRoute() }
        )
    } else {
        Icon(
            imageVector = item.defaultIcon,
            contentDescription = stringResource(item.title),
            modifier = Modifier
                .size(30.dp)
                .noRippleClickable { onNavigateToRoute() }
        )
    }
}