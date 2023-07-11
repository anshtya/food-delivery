package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Cart(
    onFoodClick: (Int) -> Unit
) {
    Button(onClick = { onFoodClick(1) }) {
        Text(text = "cart")
    }
}