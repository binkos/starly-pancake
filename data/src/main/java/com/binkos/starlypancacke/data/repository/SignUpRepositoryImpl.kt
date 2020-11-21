package com.binkos.starlypancacke.data.repository

import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.OnSuccess
import com.binkos.starlypancacke.domain.model.User
import com.binkos.starlypancacke.domain.repository.SignUpRepository

class SignUpRepositoryImpl : SignUpRepository {

    override suspend fun signUp(user: User): AuthState {
        return OnSuccess
    }
}