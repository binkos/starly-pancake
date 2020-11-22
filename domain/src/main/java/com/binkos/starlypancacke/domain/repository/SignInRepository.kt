package com.binkos.starlypancacke.domain.repository

import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.User
import kotlinx.coroutines.flow.Flow

interface SignInRepository {

    suspend fun save(email: String)

    suspend fun signIn(user: User): Flow<AuthState>

    suspend fun isAuthorized(): Boolean

    suspend fun logout()
}