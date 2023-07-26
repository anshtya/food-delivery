package com.anshtya.fooddelivery.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM food_cart")
    fun getCartItems(): Flow<List<CartEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM food_cart)")
    fun checkCartIsNotEmpty(): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM food_cart WHERE foodId =:id )")
    suspend fun checkItemExist(id: Int): Boolean

    @Query("SELECT * FROM food_cart WHERE foodId =:itemId")
    suspend fun getCartItem(itemId: Int): CartEntity

    @Query("SELECT SUM(totalPrice) FROM food_cart")
    fun getTotalPrice(): Flow<Double>

    @Query("SELECT SUM(quantity) FROM food_cart")
    fun getTotalItems(): Flow<Int>

    @Insert
    suspend fun insertCartItem(cartItem: CartEntity)

    @Query("UPDATE food_cart SET quantity =:newQuantity, totalPrice =:newTotalPrice WHERE id =:itemId")
    suspend fun changeItemQuantity(itemId: Int, newQuantity: Int, newTotalPrice: Double)

    @Delete
    suspend fun deleteCartItem(cartItem: CartEntity)
}