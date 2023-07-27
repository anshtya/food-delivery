package com.anshtya.fooddelivery.ui.screens.home.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.components.FoodItem
import com.anshtya.fooddelivery.ui.components.LocationBar

@Composable
fun Feed(
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    feedViewModel: FeedViewModel = hiltViewModel()
) {
    val recommendedFoodItems = remember { feedViewModel.recommendedFoodList }

    Column(modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
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