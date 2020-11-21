package com.cobeisfresh.template.di

import com.cobeisfresh.template.routing.app.AppRouter
import com.cobeisfresh.template.app.AppViewModel
import com.example.data.common.coroutine.CoroutineContextProvider
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val APP_MODULE = "APP_MODULE"

val appModule = module {
    val cicerone = Cicerone.create(AppRouter())
    single { cicerone.router }
    single(named(APP_MODULE)) { cicerone.getNavigatorHolder() }
    viewModel { AppViewModel(appRouter = get()) }
    single { CoroutineContextProvider() }
}