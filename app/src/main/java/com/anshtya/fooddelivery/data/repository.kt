package com.anshtya.fooddelivery.data

object repository {
    val list: List<Food> = foodList
    fun getFood(foodId: Int): Food = foodList.find { it.id == foodId }!!
}