package com.binkos.starlypancacke.domain.repository

import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.User

interface SignUpRepository {

    suspend fun getUserByEmail(email: String): User?

    suspend fun signUp(user: User): AuthState
}