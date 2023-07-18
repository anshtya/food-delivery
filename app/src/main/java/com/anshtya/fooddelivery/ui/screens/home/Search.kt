package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import com.anshtya.fooddelivery.data.sortFilterList
import com.anshtya.fooddelivery.data.typeFilterList
import com.anshtya.fooddelivery.ui.components.FilterChipWithBottomSheet
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySearchBar
import com.anshtya.fooddelivery.ui.components.FoodItem
import kotlinx.coroutines.delay
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
    val coroutineScope = rememberCoroutineScope()
    val foodItems by Repository.foodItems.collectAsStateWithLifecycle()
    val searchFilters by Repository.filterList.collectAsStateWithLifecycle()

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
                if (text.isNotEmpty()) {
                    delay(1000L)
                }
                Repository.getSearchResults(text)
                isSearching = false
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                item(
                    key = 0
                ) {
                    val sortFilter = searchFilters[0]
                    FilterChipWithBottomSheet(
                        filterName = sortFilter.name,
                        optionList = sortFilterList,
                        selectMultipleOptions = false
                    )
                }

                item(
                    key = 1
                ) {
                    val typeFilter = searchFilters[1]
                    FilterChipWithBottomSheet(
                        filterName = typeFilter.name,
                        optionList = typeFilterList,
                        selectMultipleOptions = true
                    )
                }

                item(
                    key = 2
                ) {
                    val ratingFilter = searchFilters[2]
                    FilterChip(
                        selected = ratingFilter.isSelected,
                        onClick = {
                            coroutineScope.launch {
                                if (ratingFilter.isSelected) {
                                    Repository.showHighRatingItems(false, ratingFilter.id)
                                } else {
                                    Repository.showHighRatingItems(true, ratingFilter.id)
                                }
                            }
                        },
                        label = {
                            Text(ratingFilter.name)
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
                    key = { food -> food.id }
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