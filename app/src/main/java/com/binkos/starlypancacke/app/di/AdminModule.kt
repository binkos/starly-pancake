package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.ui.admin.AdminRouter
import com.binkos.starlypancacke.app.ui.admin.AdminViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ADMIN_FLOW_FEATURE = "ADMIN_FLOW_FEATURE"

val adminModule = module {

    val cicerone = Cicerone.create(AdminRouter())
    single { cicerone.router }
    single(named(ADMIN_FLOW_FEATURE)) { cicerone.getNavigatorHolder() }
    viewModel { (adminId: String) ->
        AdminViewModel(
            appRouter = get(),
            adminId = adminId,
            featureRouter = get(),
            getOrganizationsUseCase = get(),
            logoutUseCase = get()
        )
    }
}