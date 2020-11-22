package com.binkos.starlypancacke.app.di

import com.binkos.starlypancacke.data.repository.AuthRepositoryImpl
import com.binkos.starlypancacke.data.repository.SignUpRepositoryImpl
import com.binkos.starlypancacke.domain.repository.SignInRepository
import com.binkos.starlypancacke.domain.repository.SignUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<SignInRepository> {
        AuthRepositoryImpl(
            preferences = get(),
            contextProvider = get(),
            remoteSource = get()
        )
    }

    factory<SignUpRepository> {
        SignUpRepositoryImpl(
            remoteSource = get(),
            contextProvider = get()
        )
    }
}