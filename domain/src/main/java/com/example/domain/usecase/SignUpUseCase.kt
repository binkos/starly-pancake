package com.example.domain.usecase

import com.example.domain.model.AuthState
import com.example.domain.model.User
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.flow

class SignUpUseCase(
    private val signUpRepository: SignUpRepository
) {

    suspend fun signUp(email: String, password: String): AuthState {
        return signUpRepository.signUp(User(email, password))
    }
}