package com.binkos.starlypancacke.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal class UserDbParams {
    companion object {
        const val TABLE_NAME = "user"
        const val ID = "id"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}

@Entity(tableName = UserDbParams.TABLE_NAME)
class UserDb(
    /** It is external id. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UserDbParams.ID)
    var id: Long? = null,

    @ColumnInfo(name = UserDbParams.EMAIL)
    val email: String,

    @ColumnInfo(name = UserDbParams.PASSWORD)
    val password: String
)