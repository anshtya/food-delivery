package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Feed(
    onFoodClick: (Int) -> Unit
) {
    Button(onClick = { onFoodClick(2) }) {
        Text(text = "feed")
    }
}