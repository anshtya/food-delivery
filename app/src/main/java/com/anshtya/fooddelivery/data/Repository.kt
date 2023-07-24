package com.anshtya.fooddelivery.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object Repository {

    val list = foodList

    val cartItems: Flow<List<CartItem>> = flow { emit(cart) }

    fun getRecommendedList(): List<Food> = foodList.filter { it.rating >= 4 }.take(7)
    fun getFoodDetail(foodId: Int): Food = foodList.find { it.id == foodId }!!
    fun getSortOptions() = SortOption.values().toList()
    fun getFilterOptions() = FilterOption.values().toList()
    fun getSearchResult(list: List<Food>, query: String) = list.filter {
        it.name.contains(query, ignoreCase = true)
    }



}