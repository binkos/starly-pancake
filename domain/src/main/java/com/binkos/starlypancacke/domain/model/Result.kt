package com.binkos.starlypancacke.domain.model

sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()
class Progress<out T : Any> : Result<T>()
data class Failure<out T : Any>(val appError: ApplicationError) : Result<T>()

class AppError(val throwable: Throwable, val errorCode: Int = 0)
class ApplicationError(val reason: String, val errorCode: Int = 0)