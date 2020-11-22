package com.binkos.starlypancacke.database.core

import androidx.room.RoomDatabase
import com.binkos.starlypancacke.database.dao.UserDao
import com.binkos.starlypancacke.database.entity.UserDb
import androidx.room.Database as Db

@Db(entities = [UserDb::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}