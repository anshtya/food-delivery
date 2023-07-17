package com.anshtya.fooddelivery.data

data class Filter(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)

val filterOptions: List<Filter> = listOf(
    Filter(
        id = 1,
        name = "Sort"
    ),
    Filter(
        id = 2,
        name = "Type"
    ),
    Filter(
        id = 3,
        name = "Rating 4.0+"
    )
)

val sortFilterList: List<Filter> = listOf(
    Filter(
        id = 1,
        name = "Price: Low To High"
    ),
    Filter(
        id = 2,
        name = "Price: High to Low"
    ),
    Filter(
        id = 3,
        name = "Rating: High to Low"
    )
)

val typeFilterList: List<Filter> = listOf(
    Filter(
        id = 1,
        name = "Appetizers"
    ),
    Filter(
        id = 2,
        name = "Snacks"
    ),
    Filter(
        id = 3,
        name = "Desserts"
    )
)