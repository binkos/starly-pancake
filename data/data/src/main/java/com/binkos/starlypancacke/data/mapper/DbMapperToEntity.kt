package com.binkos.starlypancacke.data.mapper

import com.binkos.starlypancacke.database.entity.FoodCategoryDb
import com.binkos.starlypancacke.database.entity.FoodDb
import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.binkos.starlypancacke.database.entity.UserDb
import com.binkos.starlypancacke.domain.model.Food
import com.binkos.starlypancacke.domain.model.FoodCategory
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.model.User

fun UserDb.toDomain() = User(email = email, password = password)

fun OrganizationDb.toDomain() = Organization(
    name = name,
    icon = icon,
    photos = photos,
    description = description,
    latitude = latitude,
    longitude = longitude,
    menu = menu.sortedBy { it.name }.map { it.toDomain() },
    ownersId = ownersId
)

fun FoodDb.toDomain() = Food(
    name = name,
    weight = weight,
    icon = icon,
    calories = calories,
    price = price,
    category = category.toDomain(),
    ingredients = ingredients
)

fun FoodCategoryDb.toDomain(): FoodCategory {
    return when (this) {
        FoodCategoryDb.desert -> FoodCategory.DESERT
        FoodCategoryDb.meat -> FoodCategory.MEAT
        FoodCategoryDb.salad -> FoodCategory.SALAD
        FoodCategoryDb.soup -> FoodCategory.SOUP
        FoodCategoryDb.drink -> FoodCategory.DRINK
        FoodCategoryDb.fish -> FoodCategory.FISH
        FoodCategoryDb.pasta -> FoodCategory.PASTA
    }
}