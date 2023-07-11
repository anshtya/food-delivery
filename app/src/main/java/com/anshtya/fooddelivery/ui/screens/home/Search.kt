package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Search(
    onFoodClick: (Int) -> Unit
) {
    Button(onClick = { onFoodClick(3) }) {
        Text(text = "search")
    }
}