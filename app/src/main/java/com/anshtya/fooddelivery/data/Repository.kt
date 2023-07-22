package com.anshtya.fooddelivery.data

object Repository {

    val list = foodList

    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFoodDetail(foodId: Int): Food = foodList.find { it.id == foodId }!!
    fun getSortOptions() = SortOption.values().toList()
    fun getFilterOptions() = FilterOption.values().toList()

}