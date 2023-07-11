package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.ui.screens.home.HomeSections

@Composable
fun FoodDeliveryNavigationItem(
    item: HomeSections,
    currentDestination: String?,
    onItemClick: () -> Unit
) {
    val itemSelected = currentDestination == item.route
    if (itemSelected) {
        Icon(
            imageVector = item.selectedIcon,
            contentDescription = stringResource(item.title),
            modifier = Modifier
                .size(30.dp)
                .noRippleClickable { onItemClick() }
        )
    } else {
        Icon(
            imageVector = item.defaultIcon,
            contentDescription = stringResource(item.title),
            modifier = Modifier
                .size(30.dp)
                .noRippleClickable { onItemClick() }
        )
    }
}

inline fun Modifier.noRippleClickable(
    crossinline onItemClick: () -> Unit
) = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) {
        onItemClick()
    }
}