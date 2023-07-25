package com.anshtya.fooddelivery.ui.screens.fooddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.fooddelivery.data.repository.CartRepository
import com.anshtya.fooddelivery.data.local.model.Food
import com.anshtya.fooddelivery.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val cartRepository: CartRepository
): ViewModel() {

    fun getFoodDetail(foodId: Int) = foodRepository.getFoodDetail(foodId)

    fun addItemToCart(food: Food, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addCartItem(food, quantity)
        }
    }
}