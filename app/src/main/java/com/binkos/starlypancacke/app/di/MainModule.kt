package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.ui.main.MainFlowRouter
import com.binkos.starlypancacke.app.ui.main.MainMapViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAIN_FLOW_FEATURE = "MAIN_FLOW_FEATURE"

val mainModule = module {
    val cicerone = Cicerone.create(MainFlowRouter())
    single { cicerone.router }
    single(named(MAIN_FLOW_FEATURE)) { cicerone.getNavigatorHolder() }
    viewModel {
        MainMapViewModel(
            featureRouter = get(),
            appRouter = get(),
            getOrganizationsUseCase = get(),
            logoutUseCase = get()
        )
    }
}