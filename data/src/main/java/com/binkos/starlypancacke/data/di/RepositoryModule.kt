package com.binkos.starlypancacke.data.di

import com.binkos.starlypancacke.data.repository.AuthRepositoryImpl
import com.binkos.starlypancacke.data.repository.SignUpRepositoryImpl
import com.binkos.starlypancacke.domain.repository.SignInRepository
import com.binkos.starlypancacke.domain.repository.SignUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<SignInRepository> { AuthRepositoryImpl(preferences = get(), contextProvider = get()) }
    factory<SignUpRepository> { SignUpRepositoryImpl() }
}