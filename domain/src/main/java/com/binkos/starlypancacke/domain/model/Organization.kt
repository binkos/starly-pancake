package com.binkos.starlypancacke.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Organization(
    val name: String,
    val icon: String,
    val photos: List<String>,
    val description: String,
    var menu: List<Food>,
    val latitude: Double,
    val longitude: Double,
    val ownersId: String
)