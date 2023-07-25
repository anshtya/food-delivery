package com.anshtya.fooddelivery.data.local.model

enum class SortOption(
    val optionName: String
) {
    Default(""),
    ByPriceAscending("Price: Low to High"),
    ByPriceDescending("Price: High to Low"),
    ByRatingDescending("Rating: High to Low")
}

enum class FilterOption {
    Appetizers,
    Snacks,
    Desserts
}