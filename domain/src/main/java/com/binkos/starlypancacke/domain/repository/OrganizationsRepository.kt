package com.binkos.starlypancacke.domain.repository

import com.binkos.starlypancacke.domain.model.Organization
import kotlinx.coroutines.flow.Flow

interface OrganizationsRepository {

    suspend fun get(): List<Organization>

    suspend fun getByName(name: String): Flow<Organization>

    suspend fun add(organization: Organization)
}