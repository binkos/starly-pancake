package com.binkos.starlypancacke.app.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binkos.starlypancacke.app.ui.admin.AdminRouter
import com.binkos.starlypancacke.domain.model.Food
import com.binkos.starlypancacke.domain.usecase.CreateFoodUseCase
import kotlinx.coroutines.launch

class FoodViewModel(
    private val featureRouter: AdminRouter,
    private val createFoodUseCase: CreateFoodUseCase
) : ViewModel() {

    fun createFood(
        name: String,
        description: String,
        photo: String,
        price: String,
        calories: String
    ) {
        if (verifyInfo(name, description, photo, price, calories)) {
            viewModelScope.launch {
                createFoodUseCase.execute(
                    Food(
                        name = name,
                        icon = photo,
                        calories = calories.toInt(),
                        ingredients = description.split(","),
                        price = price.toInt()
                    )
                )
            }
        }
    }

    private fun verifyInfo(
        name: String,
        description: String,
        photo: String = "",
        price: String,
        calories: String
    ): Boolean {
        return name.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty() && calories.isNotEmpty()
    }

    fun exit() {
        featureRouter.exit()
    }
}