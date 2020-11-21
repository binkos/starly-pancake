package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.repository.SignInRepository
import com.example.domain.repository.SignUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<SignInRepository> { AuthRepositoryImpl(preferences = get(), contextProvider = get()) }
    factory<SignUpRepository> { SignUpRepositoryImpl() }
}