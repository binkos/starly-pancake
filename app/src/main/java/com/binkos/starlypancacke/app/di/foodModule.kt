package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.ui.menu.FoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val foodModule = module {
    viewModel {
        FoodViewModel(
            featureRouter = get(),
            createFoodUseCase = get()
        )
    }
}