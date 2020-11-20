package com.example.domain.usecase

import com.example.domain.repository.SignInRepository

class SignInUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun signIn(email: String) = signInRepository.save(email)
}