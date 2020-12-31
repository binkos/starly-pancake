package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.domain.usecase.*
import org.koin.dsl.module

val interactionModule = module {
    factory { SignInUseCase(signInRepository = get()) }
    factory { GetAuthorizeUseCase(signInRepository = get()) }
    factory { SignUpUseCase(signUpRepository = get()) }
    factory { GetOrganizationsUseCase(organizationsRepository = get()) }
    factory { LogoutUseCase(signInRepository = get()) }
    factory { GetAdminUseCase(signInRepository = get()) }
    factory { CreateOrganizationsUseCase(organizationsRepository = get()) }
    factory { CreateFoodUseCase(organizationsRepository = get()) }
}
