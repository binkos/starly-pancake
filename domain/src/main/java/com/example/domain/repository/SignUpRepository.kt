package com.example.domain.repository

import com.example.domain.model.AuthState
import com.example.domain.model.User

interface SignUpRepository {

    suspend fun signUp(user: User): AuthState
}