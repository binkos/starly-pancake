package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.app.ui.organization.CreateOrganizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val organizationModule = module {
    viewModel {
        CreateOrganizationViewModel(
            featureRouter = get(),
            getAdminIdUseCase = get(),
            createOrganizationUseCase = get()
        )
    }

}