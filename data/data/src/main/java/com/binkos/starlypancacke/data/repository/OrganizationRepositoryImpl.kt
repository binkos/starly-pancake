package com.binkos.starlypancacke.data.repository

import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.binkos.starlypancacke.database.source.RemoteSource
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.repository.OrganizationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OrganizationRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val contextProvider: CoroutineContextProvider
) : OrganizationsRepository {

    override suspend fun get(): Flow<List<Organization>> {
        return flowOf(
            listOf(
                Organization("1", 53.909581, 27.42733),
                Organization("2", 53.909181, 27.42533),
                Organization("3", 53.909981, 27.42433),
                Organization("4", 53.907581, 27.4283),
                Organization("5", 53.906581, 27.42133)
            )
        )
//        return remoteSource
//            .getOrganizations()
//            .map { it.toDomain()}
    }

    override suspend fun getByName(name: String): Flow<Organization> {

        return flowOf(
            Organization("Huy", 53.907581, 27.4283)
        )

//        return remoteSource
//            .getOrganizationByName(name)
//            .map { it.toDomain() }
    }

    override suspend fun add(organization: Organization) {
        TODO("Not yet implemented")
    }
}