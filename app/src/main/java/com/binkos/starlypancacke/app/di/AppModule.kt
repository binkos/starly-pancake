package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.app.core.AppViewModel
import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val APP_MODULE = "APP_MODULE"

val appModule = module {
    val cicerone = Cicerone.create(AppRouter())
    single { cicerone.router }
    single(named(APP_MODULE)) { cicerone.getNavigatorHolder() }
    viewModel { AppViewModel(appRouter = get(),getAuthorizeUseCase = get()) }
    single { CoroutineContextProvider() }
}