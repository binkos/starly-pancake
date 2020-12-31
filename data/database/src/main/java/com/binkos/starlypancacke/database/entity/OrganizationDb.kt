package com.binkos.starlypancacke.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.binkos.starlypancacke.database.DatabaseConverters

internal class OrganizationDbParams {
    companion object {
        const val TABLE_NAME = "organization"
        const val ID = "id"
        const val ICON = "icon"
        const val PHOTOS = "photos"
        const val DESCRIPTION = "description"
        const val NAME = "name"
        const val LONGITUDE = "longitude"
        const val LATITUDE = "latitude"
        const val MENU = "menu"
        const val OWNER_ID = "owner_id"
    }
}

@Entity(tableName = OrganizationDbParams.TABLE_NAME)
@TypeConverters(DatabaseConverters::class)
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
    val longitude: Double,

    @ColumnInfo(name = OrganizationDbParams.ICON)
    val icon: String,

    @ColumnInfo(name = OrganizationDbParams.PHOTOS)
    val photos: List<String>,

    @ColumnInfo(name = OrganizationDbParams.DESCRIPTION)
    val description: String,

    @ColumnInfo(name = OrganizationDbParams.MENU)
    val menu: List<FoodDb>,

    @ColumnInfo(name = OrganizationDbParams.OWNER_ID)
    val ownersId: String
)