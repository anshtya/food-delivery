package com.anshtya.fooddelivery.ui.screens.home.feed

import androidx.lifecycle.ViewModel
import com.anshtya.fooddelivery.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    foodRepository: FoodRepository
): ViewModel() {
    val recommendedFoodList = foodRepository.getRecommendedList()
}