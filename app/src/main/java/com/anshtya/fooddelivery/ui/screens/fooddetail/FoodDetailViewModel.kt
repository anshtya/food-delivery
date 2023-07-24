package com.anshtya.fooddelivery.ui.screens.fooddetail

import androidx.lifecycle.ViewModel
import com.anshtya.fooddelivery.data.Repository

class FoodDetailViewModel : ViewModel() {
    private val repository = Repository

    fun getFoodDetail(foodId: Int) = repository.getFoodDetail(foodId)
    // fun to get updated quantity
    // fun to change quantity

}