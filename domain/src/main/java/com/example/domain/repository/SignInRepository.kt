package com.example.domain.repository

interface SignInRepository {

    suspend fun save(email: String)

    suspend fun isAuthorized(): Boolean

    suspend fun logout()
}