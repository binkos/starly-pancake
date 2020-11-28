package com.binkos.starlypancacke.data.mapper

import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.binkos.starlypancacke.database.entity.UserDb
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.model.User

fun UserDb.toDomain() = User(email = email, password = password)

fun OrganizationDb.toDomain() = Organization(name, longitude, latitude)

fun List<OrganizationDb>.toDomain() = this.map { it.toDomain() }