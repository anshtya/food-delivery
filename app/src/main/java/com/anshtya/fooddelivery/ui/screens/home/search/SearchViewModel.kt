package com.anshtya.fooddelivery.ui.screens.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.fooddelivery.data.local.model.FilterOption
import com.anshtya.fooddelivery.data.local.model.SortOption
import com.anshtya.fooddelivery.data.repository.FoodRepository
import com.anshtya.fooddelivery.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _foodList = foodRepository.list

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
            foodRepository.getSearchResult(currentList, it)
        } ?: emptyList()
    }.flowOn(defaultDispatcher).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    fun getSortOptions() = foodRepository.getSortOptions()

    fun getFilterOptions() = foodRepository.getFilterOptions()

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