package com.anshtya.fooddelivery.data

data class Food(
    val id: Int,
    val name: String,
    val rating: Double,
    val price: Double,
    val type: String = ""
)

val foodList: List<Food> = listOf(
    Food(
        id = 1,
        name = "Burger",
        rating = 4.5,
        price = 5.99
    ),
    Food(
        id = 2,
        name = "French Fries",
        rating = 4.0,
        type = "Appetizers",
        price = 2.99
    ),
    Food(
        id = 3,
        name = "Pizza",
        rating = 4.5,
        price = 11.99
    ),
    Food(
        id = 4,
        name = "Hot dog",
        rating = 3.5,
        price = 3.99
    ),
    Food(
        id = 5,
        name = "Chicken wings",
        rating = 4.0,
        price = 7.99
    ),
    Food(
        id = 6,
        name = "Nachos",
        rating = 3.5,
        type = "Appetizers",
        price = 6.99
    ),
    Food(
        id = 7,
        name = "Tacos",
        rating = 4.0,
        price = 4.99
    ),
    Food(
        id = 8,
        name = "Burritos",
        rating = 4.0,
        price = 8.99
    ),
    Food(
        id = 9,
        name = "Chicken nuggets",
        rating = 3.5,
        type = "Appetizers",
        price = 4.99
    ),
    Food(
        id = 10,
        name = "Onion rings",
        rating = 3.0,
        type = "Appetizers",
        price = 3.99
    ),
    Food(
        id = 11,
        name = "Mozzarella sticks",
        rating = 3.5,
        type = "Appetizers",
        price = 4.99
    ),
    Food(
        id = 12,
        name = "Popcorn",
        rating = 3.0,
        type = "Snacks",
        price = 1.99
    ),
    Food(
        id = 13,
        name = "Soft Pretzel",
        rating = 3.9,
        type = "Snacks",
        price = 2.99
    ),
    Food(
        id = 14,
        name = "Donuts",
        rating = 4.1,
        type = "Desserts",
        price = 2.99
    ),
    Food(
        id = 15,
        name = "Ice cream",
        rating = 4.0,
        type = "Desserts",
        price = 3.99
    ),
    Food(
        id = 16,
        name = "Milkshake",
        rating = 5.0,
        type = "Desserts",
        price = 5.99
    ),
    Food(
        id = 17,
        name = "Fried chicken",
        rating = 4.2,
        price = 7.99
    ),
    Food(
        id = 18,
        name = "Sushi rolls",
        rating = 4.0,
        type = "Desserts",
        price = 9.99
    ),
    Food(
        id = 19,
        name = "Spring rolls",
        rating = 3.5,
        type = "Snacks",
        price = 4.99
    ),
    Food(
        id = 20,
        name = "Quesadillas",
        rating = 4.0,
        price = 5.99
    )
)