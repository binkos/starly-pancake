package com.binkos.starlypancacke.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val name: String = "",
    val icon: String = "",
    val weight: Int = 0,
    val calories: Int = 0,
    val price: Int = 0,
    val category: FoodCategory = FoodCategory.SALAD,
    val ingredients: List<String> = emptyList()
)
