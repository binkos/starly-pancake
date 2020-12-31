package com.binkos.starlypancacke.domain.usecase

import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository

class CreateOrganizationsUseCase(
    private val organizationsRepository: OrganizationsRepository
) {

    suspend fun execute(organization: Organization) {
        organizationsRepository.add(organization)
    }
}