package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.repository.SignInRepository

class GetAuthorizeUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun isUserAuthorized(): Boolean {
        return signInRepository.isAuthorized()
    }

}