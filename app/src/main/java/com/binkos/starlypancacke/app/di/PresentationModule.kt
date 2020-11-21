package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(getAuthorizeUseCase = get(), appRouter = get()) }
}