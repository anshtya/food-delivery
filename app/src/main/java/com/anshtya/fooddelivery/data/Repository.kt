package com.anshtya.fooddelivery.data

object Repository {
    fun getFoodItems(): List<Food> = foodList
    fun getFilters(): List<String> = filters
    fun getFood(foodId: Int): Food = foodList.find { it.id == foodId }!!
}