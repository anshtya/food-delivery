package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshtya.fooddelivery.data.Repository
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySearchBar
import com.anshtya.fooddelivery.ui.components.FoodItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val searchFilters = remember { Repository.getFilters() }
    val selectedSearchFilters = remember { mutableSetOf<Int>() }
    val coroutineScope = rememberCoroutineScope()
    val foodItems by Repository.foodItems.collectAsStateWithLifecycle()
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
                Repository.getSearchResults(text)
                isSearching = false
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                itemsIndexed(searchFilters) { index, filter ->
                    FilterChip(
                        selected = selectedSearchFilters.contains(index),
                        onClick = {
                            when (filter) {
                                searchFilters[0] -> {}
                                searchFilters[1] -> {}

                                searchFilters[2] -> {
                                    if(selectedSearchFilters.contains(index)) {
                                        selectedSearchFilters.remove(index)
                                        coroutineScope.launch { Repository.showHighRatingItems(false) }
                                    } else {
                                        selectedSearchFilters.add(index)
                                        coroutineScope.launch { Repository.showHighRatingItems(true) }
                                    }
                                }
                            }
                        },
                        label = {
                            Text(filter)
                        },
                        trailingIcon = {
                            if(filter != searchFilters[2]) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = foodItems,
                ) { item ->
                    FoodItem(
                        item = item,
                        onFoodClick = onFoodClick
                    )
                }
            }
        }
    }
}