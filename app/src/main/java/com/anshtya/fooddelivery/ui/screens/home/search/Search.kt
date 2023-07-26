package com.anshtya.fooddelivery.ui.screens.home.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.data.local.model.SortOption
import com.anshtya.fooddelivery.ui.components.FilterOptionsBottomSheet
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySearchBar
import com.anshtya.fooddelivery.ui.components.FoodItem
import com.anshtya.fooddelivery.ui.components.SortOptionBottomSheet
import com.anshtya.fooddelivery.ui.screens.home.HomeSections

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    var selectedSheetContent by remember { mutableStateOf(Option.SORT) }
    val sortOptions = remember { searchViewModel.getSortOptions() }
    val filterOptions = remember { searchViewModel.getFilterOptions() }
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    val foodItems by searchViewModel.filteredAndSortedList.collectAsStateWithLifecycle()
    val searchResults by searchViewModel.searchResults.collectAsStateWithLifecycle()
    val selectedSortOption by searchViewModel.sortOption.collectAsStateWithLifecycle()
    val selectedFilterOptions by searchViewModel.filterOptions.collectAsStateWithLifecycle()
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()

    BackHandler(isBottomSheetOpen) {
        isBottomSheetOpen = false
    }

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
                text = searchQuery,
                onValueChange = { searchViewModel.updateSearchQuery(it) },
                onClearText = { searchViewModel.clearSearchQuery() },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                FilterChip(
                    selected = selectedSortOption != SortOption.Default,
                    label = {
                        Text(
                            text = stringResource(R.string.sort)
                        )
                    },
                    onClick = {
                        selectedSheetContent = Option.SORT
                        isBottomSheetOpen = true
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                )

                FilterChip(
                    selected = selectedFilterOptions.isNotEmpty(),
                    label = {
                        Text(
                            text = stringResource(R.string.filter)
                        )
                    },
                    onClick = {
                        selectedSheetContent = Option.FILTER
                        isBottomSheetOpen = true
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                if (searchQuery.isNotEmpty()) {
                    if (searchResults.isEmpty()) {
                        item {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    stringResource(R.string.no_results)
                                )
                            }
                        }
                    } else {
                        items(
                            items = searchResults
                        ) { item ->
                            FoodItem(
                                item = item,
                                onFoodClick = onFoodClick
                            )
                        }
                    }
                } else {
                    items(
                        items = foodItems
                    ) { item ->
                        FoodItem(
                            item = item,
                            onFoodClick = onFoodClick
                        )
                    }
                }
            }
        }

        if (isBottomSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetOpen = false }
            ) {
                when (selectedSheetContent) {
                    Option.SORT -> {
                        SortOptionBottomSheet(
                            sortOptions = sortOptions,
                            selectedSortOption = selectedSortOption,
                            onApplySortOption = { searchViewModel.applySortOption(it) },
                            onClearSortOption = { searchViewModel.clearSortOption() },
                            modifier = modifier.height(250.dp)
                        )
                    }

                    Option.FILTER -> {
                        FilterOptionsBottomSheet(
                            filterOptions = filterOptions,
                            selectedFilterOptions = selectedFilterOptions,
                            onFilterOptionClick = { searchViewModel.setFilterOption(it) },
                            onClearFilterOption = { searchViewModel.clearFilterOptions() },
                            modifier = modifier.height(250.dp)
                        )
                    }
                }
            }
        }
    }
}

enum class Option { SORT, FILTER }