package com.anshtya.fooddelivery.data

data class Food(
    val id: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val price: Double,
    val type: String = ""
)

val filters: List<String> = listOf("Sort", "Type", "Rating 4.0+")

val foodList: List<Food> = listOf(
    Food(
        id = 1,
        name = "Burger",
        image = "https://source.unsplash.com/5ZR4DxAG3RQ",
        rating = 4.5,
        price = 5.99
    ),
    Food(
        id = 2,
        name = "French Fries",
        image = "https://source.unsplash.com/vi0kZuoe0-8",
        rating = 4.0,
        type = "Appetizers",
        price = 2.99
    ),
    Food(
        id = 3,
        name = "Pizza",
        image = "https://source.unsplash.com/MQUqbmszGGM",
        rating = 4.5,
        price = 11.99
    ),
    Food(
        id = 4,
        name = "Hot Dog",
        image = "https://source.unsplash.com/Z2cAwmIKDxA",
        rating = 3.5,
        price = 3.99
    ),
    Food(
        id = 5,
        name = "Chicken Wings",
        image = "https://source.unsplash.com/gE28aTnlqJA",
        rating = 4.0,
        price = 7.99
    ),
    Food(
        id = 6,
        name = "Nachos",
        image = "https://source.unsplash.com/3UUu_WDdOXw",
        rating = 3.5,
        type = "Appetizers",
        price = 6.99
    ),
    Food(
        id = 7,
        name = "Tacos",
        image = "https://source.unsplash.com/wIqpmuOloVA",
        rating = 4.0,
        price = 4.99
    ),
    Food(
        id = 8,
        name = "Burritos",
        image = "https://source.unsplash.com/p-O37cSAV_4",
        rating = 4.0,
        price = 8.99
    ),
    Food(
        id = 9,
        name = "Chicken Nuggets",
        image = "https://source.unsplash.com/P_z_xlMGuEk",
        rating = 3.5,
        type = "Appetizers",
        price = 4.99
    ),
    Food(
        id = 10,
        name = "Onion Rings",
        image = "https://source.unsplash.com/HU_ubYnwElc",
        rating = 3.0,
        type = "Appetizers",
        price = 3.99
    ),
    Food(
        id = 11,
        name = "Mozzarella Sticks",
        image = "https://source.unsplash.com/SQ1vUOxSIZc",
        rating = 3.5,
        type = "Appetizers",
        price = 4.99
    ),
    Food(
        id = 12,
        name = "Popcorn",
        image = "https://source.unsplash.com/Bz-eN8FoVPE",
        rating = 3.0,
        type = "Snacks",
        price = 1.99
    ),
    Food(
        id = 13,
        name = "Soft Pretzel",
        image = "https://source.unsplash.com/l5lCxvZfwcQ",
        rating = 3.9,
        type = "Snacks",
        price = 2.99
    ),
    Food(
        id = 14,
        name = "Donuts",
        image = "https://source.unsplash.com/Ic0E0y6Zo20",
        rating = 4.1,
        type = "Desserts",
        price = 2.99
    ),
    Food(
        id = 15,
        name = "Ice Cream",
        image = "https://source.unsplash.com/nJoiyJwBhQY",
        rating = 4.0,
        type = "Desserts",
        price = 3.99
    ),
    Food(
        id = 16,
        name = "Milkshake",
        image = "https://source.unsplash.com/X3VbbKNDMSo",
        rating = 5.0,
        type = "Desserts",
        price = 5.99
    ),
    Food(
        id = 17,
        name = "Fried Chicken",
        image = "https://source.unsplash.com/qcLlJvBK3eI",
        rating = 4.2,
        price = 7.99
    ),
    Food(
        id = 18,
        name = "Sushi Rolls",
        image = "https://source.unsplash.com/iy_MT2ifklc",
        rating = 4.0,
        type = "Desserts",
        price = 9.99
    ),
    Food(
        id = 19,
        name = "Spring Rolls",
        image = "https://source.unsplash.com/UkudQyyeovs",
        rating = 3.5,
        type = "Snacks",
        price = 4.99
    ),
    Food(
        id = 20,
        name = "Quesadillas",
        image = "https://source.unsplash.com/pvTgyioFSTY",
        rating = 4.0,
        price = 5.99
    )
)