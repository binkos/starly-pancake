package com.example.data.repository

import com.example.domain.model.AuthState
import com.example.domain.model.OnSuccess
import com.example.domain.model.User
import com.example.domain.repository.SignUpRepository

class SignUpRepositoryImpl : SignUpRepository {

    override suspend fun signUp(user: User): AuthState {
        return OnSuccess
    }
}