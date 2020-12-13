package com.binkos.starlypancacke.domain.model

data class Organization(
    val name: String,
    val icon: String,
    val photos: List<String>,
    val description: String,
    val menu: List<Food>,
    val latitude: Double,
    val longitude: Double
)