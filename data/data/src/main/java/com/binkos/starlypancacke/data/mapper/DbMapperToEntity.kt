package com.binkos.starlypancacke.data.mapper

import com.binkos.starlypancacke.database.entity.UserDb
import com.binkos.starlypancacke.domain.model.User

fun UserDb.toDomain() = User(email = email, password = password)