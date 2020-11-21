package com.example.domain.model

sealed class AuthState
object OnSuccess : AuthState()
data class OnFailure(val reason: FailureReason) : AuthState()

enum class FailureReason(val error: String) {
    ACCOUNT_EXISTED(error = "ACCOUNT_EXISTED"),
    INVALID_PASSWORD(error = "INVALID_PASSWORD"),
    ERROR_IN_PASSWORD(error = "ERROR_IN_PASSWORD")
}