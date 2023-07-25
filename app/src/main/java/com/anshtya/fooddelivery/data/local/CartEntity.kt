package com.anshtya.fooddelivery.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "food_cart"
)
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodId: Int,
    val foodName: String,
    val foodPrice: Double,
    val quantity: Int,
    val totalPrice: Double
)
