package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.repository.SignInRepository

class GetAdminUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun execute(): String? {
        return signInRepository.getCurrentUserId()
    }
}