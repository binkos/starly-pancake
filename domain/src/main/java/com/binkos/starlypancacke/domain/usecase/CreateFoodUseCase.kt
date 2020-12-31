package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.Food
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository

class CreateFoodUseCase(private val organizationsRepository: OrganizationsRepository) {

    suspend fun execute(food: Food) {
        organizationsRepository.addFood(food)
    }
}