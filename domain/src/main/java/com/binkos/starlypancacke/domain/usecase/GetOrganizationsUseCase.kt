package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository
import kotlinx.coroutines.flow.Flow

class GetOrganizationsUseCase(
    private val organizationsRepository: OrganizationsRepository
) {

    suspend fun execute(): Flow<List<Organization>> {
        return organizationsRepository.get()
    }

    suspend fun getOrganization(name: String): Flow<Organization> {
        return organizationsRepository.getByName(name)
    }
}