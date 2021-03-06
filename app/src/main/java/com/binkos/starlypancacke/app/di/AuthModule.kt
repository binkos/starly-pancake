package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.ui.auth.head.AuthRouter
import com.binkos.starlypancacke.app.ui.auth.head.AuthViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val AUTH_FEATURE = "AUTH_FEATURE"

val authModule = module {

    val cicerone = Cicerone.create(AuthRouter())
    single { cicerone.router }
    single(named(AUTH_FEATURE)) { cicerone.getNavigatorHolder() }
    viewModel {
        AuthViewModel(
            featureRouter = get(),
            authUseCase = get(),
            appRouter = get(),
            signUpUseCase = get()
        )
    }
}