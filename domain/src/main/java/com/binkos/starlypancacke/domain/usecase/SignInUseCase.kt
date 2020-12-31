package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.User
import com.binkos.starlypancacke.domain.repository.SignInRepository
import kotlinx.coroutines.flow.Flow

class SignInUseCase(
    private val signInRepository: SignInRepository
) {

    suspend fun signIn(email: String, password: String): Flow<AuthState> {
        return signInRepository.signIn(User(email, password))
    }

    suspend fun save(email: String) {
        signInRepository.save(email)
    }

    suspend fun saveAdmin(email: String) {
        signInRepository.saveAdmin(email)
    }
}