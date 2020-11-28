package com.binkos.starlypancacke.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.binkos.starlypancacke.database.entity.OrganizationDb
import com.binkos.starlypancacke.database.entity.OrganizationDbParams
import kotlinx.coroutines.flow.Flow

@Dao
abstract class OrganizationDao {

    @Transaction
    @Query("SELECT * FROM ${OrganizationDbParams.TABLE_NAME}")
    abstract fun observeOrganizations(): Flow<List<OrganizationDb>>

    @Transaction
    @Query("SELECT * FROM ${OrganizationDbParams.TABLE_NAME} WHERE ${OrganizationDbParams.NAME} = (:name)")
    abstract fun observeOrganization(name: String): Flow<OrganizationDb>
}