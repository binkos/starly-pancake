package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.FailureReason
import com.binkos.starlypancacke.domain.model.OnFailure
import com.binkos.starlypancacke.domain.model.User
import com.binkos.starlypancacke.domain.repository.SignUpRepository

class SignUpUseCase(
    private val signUpRepository: SignUpRepository
) {

    suspend fun signUp(email: String, password: String): AuthState {
        val isUserExist = signUpRepository.getUserByEmail(email) != null
        return if (isUserExist) {
            OnFailure(FailureReason.ACCOUNT_EXISTED)
        } else {
            signUpRepository.signUp(User(email, password))
        }
    }
}