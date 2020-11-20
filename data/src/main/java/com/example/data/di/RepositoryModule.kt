package com.example.data.di

import com.example.data.common.utils.Connectivity
import com.example.data.common.utils.ConnectivityImpl
import com.example.data.repository.AuthRepositoryImpl
import com.example.domain.repository.SignInRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
    factory<SignInRepository> { AuthRepositoryImpl(preferences = get(), contextProvider = get()) }
}