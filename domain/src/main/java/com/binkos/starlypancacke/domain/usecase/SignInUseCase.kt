package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.repository.SignInRepository

class SignInUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun signIn(email: String) = signInRepository.save(email)
}