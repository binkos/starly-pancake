package com.binkos.starlypancacke.data.repository

import android.content.SharedPreferences
import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.binkos.starlypancacke.data.mapper.toDomain
import com.binkos.starlypancacke.database.source.RemoteSource
import com.binkos.starlypancacke.domain.model.*
import com.binkos.starlypancacke.domain.repository.SignInRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

private const val USER_EMAIL = "USER_EMAIL"
private const val ADMIN_EMAIL = "ADMIN_EMAIL"

class AuthRepositoryImpl(
    private val preferences: SharedPreferences,
    private val contextProvider: CoroutineContextProvider,
    private val remoteSource: RemoteSource
) : SignInRepository {

    override suspend fun save(email: String) {
        withContext(contextProvider.io) {
            preferences.edit().clear().apply()
            preferences.edit().putString(USER_EMAIL, email).apply()
        }
    }

    override suspend fun signIn(user: User): Flow<AuthState> {
        return flow {
            withContext(contextProvider.io) {
                val dbUser = remoteSource.getUserByEmail(user.email)?.toDomain()
                if (dbUser != null && user == dbUser) {
                    emit(OnSuccess)
                } else emit(OnFailure(FailureReason.INVALID_CREDENTIALS))
            }
        }
    }

    override suspend fun isAuthorized(): AuthUserStatus {
        return withContext(contextProvider.io) {
            preferences.getString(ADMIN_EMAIL, null)?.let {
                return@withContext AuthUserStatus.ADMIN.apply { data = it }
            }
            if (
                preferences.getString(USER_EMAIL, null) != null
            ) return@withContext AuthUserStatus.USER
            return@withContext AuthUserStatus.NONE
        }
    }

    override suspend fun logout() {
        withContext(contextProvider.io) {
            preferences.edit().clear().apply()
        }
    }

    override suspend fun saveAdmin(email: String) {
        preferences.edit().clear().apply()
        preferences.edit().putString(ADMIN_EMAIL, email).apply()
    }

    override suspend fun getCurrentUserId(): String? {
        return preferences.getString(ADMIN_EMAIL, null)
    }

}