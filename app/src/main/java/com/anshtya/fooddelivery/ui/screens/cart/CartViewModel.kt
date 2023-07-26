package com.anshtya.fooddelivery.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.fooddelivery.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {

    val cartItems = repository.cartItems.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun increaseQuantity(foodId: Int) {
        viewModelScope.launch {
            repository.increaseItemQuantity(foodId)
        }
    }

    fun decreaseQuantity(foodId: Int) {
        viewModelScope.launch {
            repository.decreaseItemQuantity(foodId)
        }
    }
}