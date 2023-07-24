package com.anshtya.fooddelivery.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.fooddelivery.data.FilterOption
import com.anshtya.fooddelivery.data.Repository
import com.anshtya.fooddelivery.data.SortOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    private val repository = Repository
    private val defaultDispatcher = Dispatchers.Default

    private val _foodList = repository.list

    private val _sortOption = MutableStateFlow(SortOption.Default)
    val sortOption = _sortOption.asStateFlow()

    private val _filterOptions = MutableStateFlow<List<FilterOption>>(emptyList())
    val filterOptions = _filterOptions.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val filteredAndSortedList = combine(_sortOption, _filterOptions) { sortOption, filterOptions ->
        val filteredList = _foodList.run {
            filterOptions.takeIf { it.isNotEmpty() }?.let { filterOptions ->
                this.filter { food ->
                    filterOptions.any { filter ->
                        food.type == filter.name
                    }
                }
            } ?: this
        }

        sortOption.takeIf { it != SortOption.Default }?.let {
            when (sortOption) {
                SortOption.ByPriceAscending -> filteredList.sortedBy { it.price }
                SortOption.ByPriceDescending -> filteredList.sortedByDescending { it.price }
                SortOption.ByRatingDescending -> filteredList.sortedByDescending { it.rating }

                else -> filteredList//unreachable code
            }
        } ?: filteredList

    }.flowOn(defaultDispatcher).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _foodList
    )

    val searchResults = combine(filteredAndSortedList, _searchQuery) { currentList, query ->
        query.takeIf { it.isNotEmpty() }?.let {
            repository.getSearchResult(currentList, it)
        } ?: emptyList()
    }.flowOn(defaultDispatcher).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val cartItems = repository.cartItems.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun getRecommendedFoodList() = repository.getRecommendedList()

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    fun getSortOptions() = repository.getSortOptions()

    fun getFilterOptions() = repository.getFilterOptions()

    fun applySortOption(sortOption: SortOption) {
        _sortOption.value = sortOption
    }

    fun setFilterOption(filterOption: FilterOption) {
        val appliedFilters = _filterOptions.value.toMutableList()

        if (appliedFilters.contains(filterOption)) {
            appliedFilters.remove(filterOption)
        } else {
            appliedFilters.add(filterOption)
        }

        _filterOptions.value = appliedFilters
    }

    fun clearSortOption() {
        _sortOption.value = SortOption.Default
    }

    fun clearFilterOptions() {
        _filterOptions.value = emptyList()
    }

    fun clearSearchQuery() {
        _searchQuery.value = ""
    }
}