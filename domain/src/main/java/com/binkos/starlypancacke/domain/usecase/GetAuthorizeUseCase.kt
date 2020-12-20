package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.AuthUserStatus
import com.binkos.starlypancacke.domain.repository.SignInRepository

class GetAuthorizeUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun isUserAuthorized(): AuthUserStatus {
        return signInRepository.isAuthorized()
    }
}