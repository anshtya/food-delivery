package com.anshtya.fooddelivery.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext


object Repository {

    private val mutableFoodList = MutableStateFlow(foodList)
    val foodItems = mutableFoodList.asStateFlow()
    private var oldList: List<Food> = emptyList()

    fun getFoodItems(): List<Food> = foodList
    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFilters(): List<String> = filters
    fun getFood(foodId: Int): Food = foodList.find { it.id == foodId }!!

    suspend fun getSearchResults(text: String) {
        withContext(Dispatchers.Default) {
            if (text.isEmpty()) {
                mutableFoodList.emit(foodList)
            } else {
                delay(1000L)
                mutableFoodList.emit(
                    foodList.filter { it.name.contains(text, ignoreCase = true) }
                )
            }
        }
    }

    suspend fun showHighRatingItems(show: Boolean) {
        withContext(Dispatchers.Default) {
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