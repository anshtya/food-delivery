package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.data.SortOption
import com.anshtya.fooddelivery.ui.components.FilterOptionsBottomSheet
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySearchBar
import com.anshtya.fooddelivery.ui.components.FoodItem
import com.anshtya.fooddelivery.ui.components.SortOptionBottomSheet
import com.anshtya.fooddelivery.ui.screens.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    var text by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var selectedSheetContent by remember { mutableStateOf(Option.SORT) }
    val sortOptions = remember { homeViewModel.getSortOptions() }
    val filterOptions = remember { homeViewModel.getFilterOptions() }

    val foodItems by homeViewModel.filteredAndSortedList.collectAsStateWithLifecycle()
    val selectedSortOption by homeViewModel.sortOption.collectAsStateWithLifecycle()
    val selectedFilterOptions by homeViewModel.filterOptions.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            when (selectedSheetContent) {
                Option.SORT -> {
                    SortOptionBottomSheet(
                        sortOptions = sortOptions,
                        selectedSortOption = selectedSortOption,
                        onSortOptionClick = { homeViewModel.setSortOption(it)}
                    )
                }

                Option.FILTER -> {
                    FilterOptionsBottomSheet(
                        filterOptions = filterOptions,
                        selectedFilterOptions = selectedFilterOptions,
                        onFilterOptionClick = { homeViewModel.setFilterOption(it) }
                    )
                }
            }
        }
    ) {
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
//                Repository.getSearchResults(text)
                    isSearching = false
                }

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
                            coroutineScope.launch {
                                bottomSheetState.expand()
                            }
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
                            coroutineScope.launch {
                                bottomSheetState.expand()
                            }
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
    }
}

enum class Option { SORT, FILTER }