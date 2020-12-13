package com.binkos.starlypancacke.data.mapper

import com.binkos.starlypancacke.database.entity.FoodCategoryDb
import com.binkos.starlypancacke.database.entity.FoodDb
import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.binkos.starlypancacke.database.entity.UserDb
import com.binkos.starlypancacke.domain.model.Food
import com.binkos.starlypancacke.domain.model.FoodCategory
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.model.User

fun User.toDb() = UserDb(email = this.email, password = this.password)

fun Organization.toDb() = OrganizationDb(
    name = this.name,
    latitude = this.latitude,
    longitude = this.longitude,
    icon = this.icon,
    photos = this.photos,
    description = this.description,
    menu = menu.map { it.toDb() }
)

fun Food.toDb() = FoodDb(
    name = name,
    weight = weight,
    calories = calories,
    price = price,
    category = category.toDb(),
    ingredients = ingredients,
    icon = icon
)

fun FoodCategory.toDb(): FoodCategoryDb {
    return when (this) {
        FoodCategory.DESERT -> FoodCategoryDb.desert
        FoodCategory.MEAT -> FoodCategoryDb.meat
        FoodCategory.SALAD -> FoodCategoryDb.salad
        FoodCategory.SOUP -> FoodCategoryDb.soup
        FoodCategory.DRINK -> FoodCategoryDb.drink
        FoodCategory.FISH -> FoodCategoryDb.fish
        FoodCategory.PASTA -> FoodCategoryDb.pasta
    }
}