package com.binkos.starlypancacke.database.entity

data class FoodDb(
    val name: String,
    val icon: String,
    val weight: Int,
    val calories: Int,
    val price: Int,
    val category: FoodCategoryDb,
    val ingredients: List<String>
)