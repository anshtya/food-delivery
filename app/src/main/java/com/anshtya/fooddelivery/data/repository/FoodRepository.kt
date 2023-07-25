package com.anshtya.fooddelivery.data.repository

import com.anshtya.fooddelivery.data.local.model.FilterOption
import com.anshtya.fooddelivery.data.local.model.Food
import com.anshtya.fooddelivery.data.local.model.SortOption
import com.anshtya.fooddelivery.data.local.model.foodList

class FoodRepository {

    val list = foodList

    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFoodDetail(foodId: Int): Food = foodList.find { it.id == foodId }!!
    fun getSortOptions() = SortOption.values().toList()
    fun getFilterOptions() = FilterOption.values().toList()
    fun getSearchResult(list: List<Food>, query: String) = list.filter {
        it.name.contains(query, ignoreCase = true)
    }
}