package com.binkos.starlypancacke.database.source

import com.binkos.starlypancacke.database.entity.UserDb
import kotlinx.coroutines.flow.Flow

interface RemoteSource {

    fun insertUser(user: UserDb): String

    fun getUserByEmail(email: String): UserDb?

    fun getUsers(): Flow<List<UserDb>>
}