package com.anshtya.fooddelivery.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


object Repository {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    private val mutableFoodList = MutableStateFlow(foodList)
    val foodItems = mutableFoodList.asStateFlow()
    private var oldList: List<Food> = emptyList()

    private val mutableFilterOptions = MutableStateFlow(filterOptions)
    val filterList = mutableFilterOptions.asStateFlow()

    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFood(foodId: Int): Food = foodList.find { it.id == foodId }!!

    fun getSearchResults(text: String) {
        repositoryScope.launch {
            if (text.isEmpty()) {
                mutableFoodList.emit(foodList)
            } else {
                mutableFoodList.emit(
                    foodList.filter { it.name.contains(text, ignoreCase = true) }
                )
            }
        }
    }

    fun showHighRatingItems(show: Boolean, id: Int) {
       repositoryScope.launch {
            mutableFilterOptions.emit(
                filterOptions.apply {
                    find { it.id == id }!!.isSelected = show
                }
            )
            if (show) {
                val newList = foodList.filter { it.rating >= 4 }
                oldList = mutableFoodList.value
                mutableFoodList.emit(newList)
            } else {
                val newList = oldList
                mutableFoodList.emit(newList)
            }
        }
    }
}