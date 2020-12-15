package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.repository.SignInRepository

class LogoutUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun logout() {
        signInRepository.logout()
    }
}