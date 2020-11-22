package com.binkos.starlypancacke.domain.model

sealed class AuthState
object OnSuccess : AuthState()
data class OnFailure(val reason: FailureReason) : AuthState()

enum class FailureReason(val error: String) {
    ACCOUNT_EXISTED(error = "ACCOUNT_EXISTED"),
    INVALID_PASSWORD(error = "INVALID_PASSWORD"),
    INVALID_CREDENTIALS(error = "INVALID_CREDENTIALS"),
    ERROR_IN_PASSWORD(error = "ERROR_IN_PASSWORD")
}