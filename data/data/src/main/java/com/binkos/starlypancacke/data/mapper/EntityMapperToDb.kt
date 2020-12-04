package com.binkos.starlypancacke.data.mapper

import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.binkos.starlypancacke.database.entity.UserDb
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.model.User

fun User.toDb() = UserDb(email = this.email, password = this.password)

fun Organization.toDb() =
    OrganizationDb(
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude,
        icon = this.icon,
        photos = this.photos,
        description = this.description
    )

fun List<Organization>.toDb() = this.map { it.toDb() }