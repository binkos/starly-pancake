package com.binkos.starlypancacke.database.source

import com.binkos.starlypancacke.database.dao.OrganizationDao
import com.binkos.starlypancacke.database.dao.UserDao
import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.binkos.starlypancacke.database.entity.UserDb
import kotlinx.coroutines.flow.Flow

class RoomRemoteSource(
    private val userDao: UserDao,
    private val organizationDao: OrganizationDao
) : RemoteSource {

    override fun insertUser(user: UserDb): String {
        userDao.insertUser(user)

        return user.email
    }

    override fun getUserByEmail(email: String): UserDb? {
        return userDao.getUserByEmail(email)
    }

    override fun getUsers(): Flow<List<UserDb>> {
        return userDao.observeUsers()
    }

    override fun getOrganizations(): List<OrganizationDb> {
        return organizationDao.observeOrganizations()
    }

    override fun getOrganizationByName(name: String): Flow<OrganizationDb> {
        return organizationDao.observeOrganization(name)
    }
}