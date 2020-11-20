package com.cobeisfresh.template.di

import com.cobeisfresh.template.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(getAuthorizeUseCase = get(), appRouter = get()) }
}