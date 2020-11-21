package com.example.domain.model

sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()
data class Failure(val appError: ApplicationError) : Result<Nothing>()

class AppError(val throwable: Throwable, val errorCode: Int = 0)
class ApplicationError(val reason: String, val errorCode: Int = 0)