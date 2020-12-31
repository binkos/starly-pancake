package com.binkos.starlypancacke.data.repository

import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.binkos.starlypancacke.database.source.RemoteSource
import com.binkos.starlypancacke.domain.model.Food
import com.binkos.starlypancacke.domain.model.FoodCategory
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*

class OrganizationRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val contextProvider: CoroutineContextProvider
) : OrganizationsRepository {

    val sampleOrganization = Organization(
        name = "Stiflers Mama",
        icon = "https://img.the-village.me/the-village.me/post_image-image/0DuIhJI5CCl8e_5OA2CZng-wide.jpg",
        photos = listOf(
            "https://img.the-village.me/the-village.me/post_image-image/RXbHzV7M6TAUf5-cd2zUkg-wide.jpg",
            "https://img.the-village.me/the-village.me/post_image-image/8Ed-XaC4rNjvnGQ5YgMHSg-wide.jpg",
            "https://img.the-village.me/the-village.me/post_image-image/KNlNQ9sJHCnTU25GBNQGNg-wide.jpg"
        ),
        description = "«Мама Стифлера» — знаковое для поколения двухтысячных выражение. Те, кто смотрел «Американский пирог», наверняка помнят знойную Дженнифер Кулидж в этой роли. Считается, что именно благодаря «маме Стифлера» в обиходе появилось слово «милфа» (MILF), означающее опытную взрослую женщину, которая не растеряла своей привлекательности и по-прежнему представляет интерес для мужчин (зачастую — моложе себя). Кстати, один из самых популярных поисковых запросов на порносайтах.",
        latitude = 53.909581,
        longitude = 27.4283,
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
        ),
        ownersId = "sdasds"
    )

    val linkedList: LinkedList<Organization> = LinkedList(
        listOf(
            sampleOrganization,
            Organization(
                name = "Enzo",
                icon = "https://media-cdn.tripadvisor.com/media/photo-s/11/51/9b/87/atmosphere.jpg",
                photos = listOf(
                    "https://avatars.mds.yandex.net/get-altay/2397657/2a0000017352e17fc1847b2e16d67a63b2e6/XXXL",
                    "https://carte.by/source/photos/2017/06/21/ldad6t96lcs.jpg"
                ),
                description = "«Кафе 1» – заведение формата «стейки и бургеры», расположенное в сердце Минска на модной и развивающейся улице Октябрьской.\n" +
                        "\n" +
                        "Здесь клиентов ждет внимательный персонал, отличное вино и по-настоящему вкусная еда.",
                latitude = 53.909581,
                longitude = 27.4293,
                menu = listOf(
                    Food(
                        icon = "https://eda.ru/img/eda/1200x-i/s2.eda.ru/StaticContent/Photos/160115152344/160119155957/p_O.jpg",
                        name = "Сет Бургеров",
                        weight = 800,
                        calories = 680,
                        price = 29,
                        category = FoodCategory.MEAT,
                        ingredients = listOf("Бергер свежий", "Бургер спелый", "Бургер молочный")
                    )
                ),
                ownersId = "qvdsawe"
            )
        )
    )

    override suspend fun get(): List<Organization> {
        return linkedList
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
                menu = listOf(),
                ownersId = "qwasd"
            )
        )

//        return remoteSource
//            .getOrganizationByName(name)
//            .map { it.toDomain() }
    }

    override suspend fun add(organization: Organization) {
        linkedList.add(organization)
    }

    override suspend fun addFood(food: Food) {
        linkedList.last.menu = linkedList.last.menu.toMutableList().apply { add(food) }
    }
}