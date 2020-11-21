package com.example.domain.di

import com.example.domain.usecase.GetAuthorizeUseCase
import com.example.domain.usecase.SignInUseCase
import com.example.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val interactionModule = module {
    factory { SignInUseCase(signInRepository = get()) }
    factory { GetAuthorizeUseCase(signInRepository = get()) }
    factory { SignUpUseCase(signUpRepository = get()) }
}
