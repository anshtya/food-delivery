package com.anshtya.fooddelivery.data.repository

import com.anshtya.fooddelivery.data.local.model.Food
import com.anshtya.fooddelivery.data.local.CartDao
import com.anshtya.fooddelivery.data.local.CartEntity
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    val cartItems = cartDao.getCartItems()

    suspend fun addCartItem(food: Food, quantity: Int) {
        if (cartDao.checkItemExist(food.id)) {
            val cartItem = cartDao.getCartItem(food.id)
            val newQuantity = cartItem.quantity + quantity
            val newTotalPrice = newQuantity * cartItem.foodPrice
            cartDao.changeItemQuantity(cartItem.id, newQuantity, newTotalPrice)
        } else {
            val cartItem = CartEntity(
                foodId = food.id,
                foodName = food.name,
                foodPrice = food.price,
                quantity = quantity,
                totalPrice = quantity * food.price
            )
            cartDao.insertCartItem(cartItem)
        }
    }

    suspend fun increaseItemQuantity(foodId: Int) {
        val cartItem = cartDao.getCartItem(foodId)
        val newQuantity = cartItem.quantity + 1
        val newTotalPrice = newQuantity * cartItem.foodPrice
        cartDao.changeItemQuantity(cartItem.id, newQuantity, newTotalPrice)
    }

    suspend fun decreaseItemQuantity(foodId: Int) {
        val cartItem = cartDao.getCartItem(foodId)
        if (cartItem.quantity > 1) {
            val newQuantity = cartItem.quantity - 1
            val newTotalPrice = newQuantity * cartItem.foodPrice
            cartDao.changeItemQuantity(cartItem.id, newQuantity, newTotalPrice)
        } else {
            cartDao.deleteCartItem(cartItem)
        }
    }
}