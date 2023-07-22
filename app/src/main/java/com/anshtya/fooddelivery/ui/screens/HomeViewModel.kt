package com.anshtya.fooddelivery.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.fooddelivery.data.FilterOption
import com.anshtya.fooddelivery.data.Food
import com.anshtya.fooddelivery.data.Repository
import com.anshtya.fooddelivery.data.SortOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    private val repository = Repository

    private val _foodList = repository.list

    private val _sortOption = MutableStateFlow(SortOption.Default)
    val sortOption = _sortOption.asStateFlow()

    private val _filterOptions = MutableStateFlow<List<FilterOption>>(emptyList())
    val filterOptions = _filterOptions.asStateFlow()

    val filteredAndSortedList = combine(_sortOption, _filterOptions) { sortOption, filters ->
        val filteredList = filterList(filters)
        if (sortOption == SortOption.Default ) {
            filteredList
        } else {
            filteredList.sortedBy { food ->
                applySort(sortOption, food)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _foodList
    )

    fun getFoodDetail(foodId: Int) = repository.getFoodDetail(foodId)

    fun getRecommendedFoodList() = repository.getRecommendedList()

    fun getSortOptions() = repository.getSortOptions()

    fun getFilterOptions() = repository.getFilterOptions()

    fun setSortOption(sortOption: SortOption) {
        _sortOption.value = sortOption
    }

    private fun applySort(sortOption: SortOption, food: Food): Double {
        return when (sortOption) {
            SortOption.ByPriceAscending -> food.price
            SortOption.ByPriceDescending -> -food.price
            SortOption.ByRatingDescending -> -food.rating

            else -> food.price //unreachable
        }
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

    private fun filterList(filterOptions: List<FilterOption>): List<Food> {
        return if (filterOptions.isEmpty()) {
            _foodList
        } else {
            _foodList.filter { food ->
                filterOptions.any { filter ->
                    food.type == filter.name
                }
            }
        }
    }
}