package com.binkos.starlypancacke.domain.di

import com.binkos.starlypancacke.domain.usecase.GetAuthorizeUseCase
import com.binkos.starlypancacke.domain.usecase.SignInUseCase
import com.binkos.starlypancacke.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val interactionModule = module {
    factory { SignInUseCase(signInRepository = get()) }
    factory { GetAuthorizeUseCase(signInRepository = get()) }
    factory { SignUpUseCase(signUpRepository = get()) }
}
