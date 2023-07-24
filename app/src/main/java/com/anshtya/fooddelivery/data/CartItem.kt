package com.anshtya.fooddelivery.data

data class CartItem(
    val food: Food,
    val totalPrice: Double,
    val quantity: Int
)

val cart: List<CartItem> = emptyList()