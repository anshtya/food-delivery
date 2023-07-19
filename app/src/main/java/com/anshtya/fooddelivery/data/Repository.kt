package com.anshtya.fooddelivery.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

object Repository {

    private val mutableFoodList = MutableStateFlow(foodList)
    val foodItems = mutableFoodList.asStateFlow()
    private var oldList: List<Food> = emptyList()

    private val mutableFilterOptions = MutableStateFlow(filterOptions)
    val filterList = mutableFilterOptions.asStateFlow()

    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFood(foodId: Int): Food = foodList.find { it.id == foodId }!!

    suspend fun getSearchResults(text: String) {
        withContext(Dispatchers.Default) {
            oldList = if (text.isEmpty()) {
                mutableFoodList.emit(foodList)
                foodList
            } else {
                val list = foodList.filter { it.name.contains(text, ignoreCase = true) }
                mutableFoodList.emit(list)
                list
            }
        }
    }

    suspend fun showHighRatingItems(isSelected: Boolean, id: Int) {
        withContext(Dispatchers.Default) {
            updateFilterOptions(isSelected, id)

            if (isSelected) {
                oldList = mutableFoodList.value
                val newList = oldList.filter { it.rating >= 4 }
                mutableFoodList.emit(newList)
            } else {
                val newList = oldList
                mutableFoodList.emit(newList)
            }
        }
    }

    suspend fun sortItems(selectedOptions: Set<Int>, id: Int) {
        withContext(Dispatchers.Default) {
            val newList: List<Food>

            if (selectedOptions.isNotEmpty()) {
                val filterId = selectedOptions.first()
                newList = when (sortFilterList[filterId - 1].id) {
                    1 -> {
                        oldList.sortedBy { it.price }
                    }
                    2 -> {
                        oldList.sortedByDescending { it.price }
                    }
                    3 -> {
                        oldList.sortedByDescending { it.rating }
                    }
                    else -> { oldList }
                }
                updateFilterOptions(true, id)
                mutableFoodList.emit(newList)
            } else {
                updateFilterOptions(false, id)
                mutableFoodList.emit(oldList)
            }
        }
    }

    suspend fun filterType(selectedFilter: Set<Int>, id: Int) {
        withContext(Dispatchers.Default) {
            val newList: MutableList<Food> = mutableListOf()

            if (selectedFilter.isNotEmpty()) {
                selectedFilter.forEach { id ->
                    val filter = typeFilterList[id-1]
                    newList.addAll(
                        oldList.filter { it.type == filter.name }
                    )
                }

                if (!filterOptions[id-1].isSelected) {
                    updateFilterOptions(true, id)
                }
                mutableFoodList.emit(newList)
            } else {
                updateFilterOptions(false, id)
                mutableFoodList.emit(oldList)
            }
        }
    }

    private suspend fun updateFilterOptions(isSelected: Boolean, id: Int) {
        val filterOptions = mutableFilterOptions.value.toMutableList()
        filterOptions[id - 1] = filterOptions[id - 1].copy(isSelected = isSelected)
        mutableFilterOptions.emit(filterOptions.toList())
    }
}