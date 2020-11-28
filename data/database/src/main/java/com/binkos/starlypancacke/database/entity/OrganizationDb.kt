package com.binkos.starlypancacke.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal class OrganizationDbParams {
    companion object {
        const val TABLE_NAME = "organization"
        const val ID = "id"
        const val NAME = "name"
        const val LONGITUDE = "longitude"
        const val LATITUDE = "latitude"
    }
}

@Entity(tableName = OrganizationDbParams.TABLE_NAME)
class OrganizationDb(
    /** It is external id. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = OrganizationDbParams.ID)
    var id: Long? = null,

    @ColumnInfo(name = OrganizationDbParams.NAME)
    val name: String,

    @ColumnInfo(name = OrganizationDbParams.LATITUDE)
    val latitude: Double,

    @ColumnInfo(name = OrganizationDbParams.LONGITUDE)
    val longitude: Double
)