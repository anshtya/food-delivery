package com.anshtya.fooddelivery.ui.screens.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.fooddelivery.data.repository.FoodRepository
import com.anshtya.fooddelivery.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    foodRepository: FoodRepository,
    cartRepository: CartRepository
): ViewModel() {
    val recommendedFoodList = foodRepository.getRecommendedList()

    val cartNotEmpty = cartRepository.cartNotEmpty.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    val totalItems = cartRepository.totalItems.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = 0
    )

    val totalPrice = cartRepository.totalPrice.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = 0.0
    )
}