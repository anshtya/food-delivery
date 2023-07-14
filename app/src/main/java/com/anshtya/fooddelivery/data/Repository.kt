package com.anshtya.fooddelivery.data

object Repository {
    fun getFoodItems(): List<Food> = foodList
    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFilters(): List<String> = filters
    fun getFood(foodId: Int): Food = foodList.find { it.id == foodId }!!
}