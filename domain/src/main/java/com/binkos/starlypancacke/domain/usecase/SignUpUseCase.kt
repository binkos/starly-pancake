package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.User
import com.binkos.starlypancacke.domain.repository.SignUpRepository

class SignUpUseCase(
    private val signUpRepository: SignUpRepository
) {

    suspend fun signUp(email: String, password: String): AuthState {
        return signUpRepository.signUp(User(email, password))
    }
}