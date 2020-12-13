package com.binkos.starlypancacke.data.repository

import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.binkos.starlypancacke.database.source.RemoteSource
import com.binkos.starlypancacke.domain.model.Food
import com.binkos.starlypancacke.domain.model.FoodCategory
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OrganizationRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val contextProvider: CoroutineContextProvider
) : OrganizationsRepository {

    override suspend fun get(): List<Organization> {
        return listOf(
            Organization(
                name = "q231r4g",
                icon = "https://img.the-village.me/the-village.me/post_image-image/0DuIhJI5CCl8e_5OA2CZng-wide.jpg",
                photos = listOf(
                    "https://img.the-village.me/the-village.me/post_image-image/RXbHzV7M6TAUf5-cd2zUkg-wide.jpg",
                    "https://img.the-village.me/the-village.me/post_image-image/8Ed-XaC4rNjvnGQ5YgMHSg-wide.jpg",
                    "https://img.the-village.me/the-village.me/post_image-image/KNlNQ9sJHCnTU25GBNQGNg-wide.jpg"
                ),
                description = "«Мама Стифлера» — знаковое для поколения двухтысячных выражение. Те, кто смотрел «Американский пирог», наверняка помнят знойную Дженнифер Кулидж в этой роли. Считается, что именно благодаря «маме Стифлера» в обиходе появилось слово «милфа» (MILF), означающее опытную взрослую женщину, которая не растеряла своей привлекательности и по-прежнему представляет интерес для мужчин (зачастую — моложе себя). Кстати, один из самых популярных поисковых запросов на порносайтах.",
                latitude = 53.909581,
                longitude = 27.4293,
                menu = listOf(
                    Food(
                        icon = "https://cs9.pikabu.ru/post_img/2018/01/28/8/1517143089171727077.jpg",
                        name = "Carbonara",
                        weight = 250,
                        calories = 700,
                        price = 12,
                        category = FoodCategory.PASTA,
                        ingredients = listOf("Pasta", "Cheese", "Bacon", "Garlic", "Salt", "Cream")
                    ),
                    Food(
                        icon = "https://eda.ru/img/eda/1200x-i/s2.eda.ru/StaticContent/Photos/160115152344/160119155957/p_O.jpg",
                        name = "Mushrooms Soup",
                        weight = 400,
                        calories = 480,
                        price = 8,
                        category = FoodCategory.SOUP,
                        ingredients = listOf("Mushrooms", "Cream", "Potato", "Spices")
                    )
                )
            ),
            Organization(
                name = "2",
                icon = "",
                photos = listOf(""),
                description = "",
                latitude = 53.909581,
                longitude = 27.4293,
                menu = listOf()
            ),
            Organization(
                name = "3",
                icon = "",
                photos = listOf(""),
                description = "",
                latitude = 53.909571,
                longitude = 27.42733,
                menu = listOf()

            ),
            Organization(
                name = "4",
                icon = "",
                photos = listOf(""),
                description = "",
                latitude = 53.909561,
                longitude = 27.4243,
                menu = listOf()
            ),
            Organization(
                name = "5",
                icon = "",
                photos = listOf(""),
                description = "",
                latitude = 53.909551,
                longitude = 27.425,
                menu = listOf()
            )
        )
//        return remoteSource
//            .getOrganizations()
//            .map { it.toDomain()}
    }

    override suspend fun getByName(name: String): Flow<Organization> {

        return flowOf(
            Organization(
                name = "q231r4g",
                icon = "https://img.the-village.me/the-village.me/post_image-image/0DuIhJI5CCl8e_5OA2CZng-wide.jpg",
                photos = listOf(
                    "https://img.the-village.me/the-village.me/post_image-image/RXbHzV7M6TAUf5-cd2zUkg-wide.jpg",
                    "https://img.the-village.me/the-village.me/post_image-image/8Ed-XaC4rNjvnGQ5YgMHSg-wide.jpg",
                    "https://img.the-village.me/the-village.me/post_image-image/KNlNQ9sJHCnTU25GBNQGNg-wide.jpg"
                ),
                description = "«Мама Стифлера» — знаковое для поколения двухтысячных выражение. Те, кто смотрел «Американский пирог», наверняка помнят знойную Дженнифер Кулидж в этой роли. Считается, что именно благодаря «маме Стифлера» в обиходе появилось слово «милфа» (MILF), означающее опытную взрослую женщину, которая не растеряла своей привлекательности и по-прежнему представляет интерес для мужчин (зачастую — моложе себя). Кстати, один из самых популярных поисковых запросов на порносайтах.",
                latitude = 53.909581,
                longitude = 27.4293,
                menu = listOf()
            )
        )

//        return remoteSource
//            .getOrganizationByName(name)
//            .map { it.toDomain() }
    }

    override suspend fun add(organization: Organization) {
        TODO("Not yet implemented")
    }
}