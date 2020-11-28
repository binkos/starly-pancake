package com.binkos.starlypancacke.app.di

import androidx.room.Room
import com.binkos.starlypancacke.database.core.Database
import com.binkos.starlypancacke.database.source.RemoteSource
import com.binkos.starlypancacke.database.source.RoomRemoteSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "starly_pancake"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), Database::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    factory { get<Database>().userDao() }
    factory { get<Database>().organizationDao() }
    single<RemoteSource> {
        RoomRemoteSource(
            userDao = get(),
            organizationDao = get()
        )
    }
}