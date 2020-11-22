package com.binkos.starlypancacke.domain.model

data class User(
    val email: String,
    val password: String
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is User -> other.email == email && other.password == password
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}