package com.example.domain.usecase

import com.example.domain.repository.SignInRepository

class GetAuthorizeUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun isUserAuthorized(): Boolean {
        return signInRepository.isAuthorized()
    }

}