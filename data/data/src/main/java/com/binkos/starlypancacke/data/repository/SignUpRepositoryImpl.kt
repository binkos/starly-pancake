package com.binkos.starlypancacke.data.repository

import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.binkos.starlypancacke.data.mapper.toDb
import com.binkos.starlypancacke.data.mapper.toDomain
import com.binkos.starlypancacke.database.source.RemoteSource
import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.OnSuccess
import com.binkos.starlypancacke.domain.model.User
import com.binkos.starlypancacke.domain.repository.SignUpRepository
import kotlinx.coroutines.withContext

class SignUpRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val contextProvider: CoroutineContextProvider
) : SignUpRepository {

    override suspend fun getUserByEmail(email: String): User? {
        return withContext(contextProvider.io) { remoteSource.getUserByEmail(email) }?.toDomain()

    }

    override suspend fun signUp(user: User): AuthState {
        return withContext(contextProvider.io) {
            remoteSource.insertUser(user.toDb())
            OnSuccess
        }
    }
}