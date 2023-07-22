package com.anshtya.fooddelivery.data

enum class SortOption {
    Default,
    ByPriceAscending,
    ByPriceDescending,
    ByRatingDescending
}

enum class FilterOption(
    name: String
) {
    Appetizers("Appetizers"),
    Snacks("Snacks"),
    Desserts("Desserts")
}