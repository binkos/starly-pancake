package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetOrganizationsUseCase(
    private val organizationsRepository: OrganizationsRepository
) {

    suspend fun execute(): Flow<List<Organization>> {
        return flowOf(organizationsRepository.get())
    }

    suspend fun find(name: String): Flow<List<Organization>> {
        return flowOf(
            organizationsRepository
            .get()
            .filter { it.name == name }
        )
    }

    suspend fun getOrganization(name: String): Flow<Organization> {
        return organizationsRepository.getByName(name)
    }
}