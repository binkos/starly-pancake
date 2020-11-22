package com.binkos.starlypancacke.database.dao

import androidx.room.*
import com.binkos.starlypancacke.database.entity.UserDb
import com.binkos.starlypancacke.database.entity.UserDbParams
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: UserDb)

    @Transaction
    @Query("SELECT * FROM ${UserDbParams.TABLE_NAME}")
    abstract fun observeUsers(): Flow<List<UserDb>>

    @Transaction
    @Query("SELECT * FROM ${UserDbParams.TABLE_NAME} WHERE ${UserDbParams.EMAIL} = (:email)")
    abstract fun getUserByEmail(email: String): UserDb?
}