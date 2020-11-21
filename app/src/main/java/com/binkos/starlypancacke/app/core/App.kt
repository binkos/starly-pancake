package com.binkos.starlypancacke.app.core

import android.app.Application
import com.binkos.starlypancacke.app.di.appModule
import com.binkos.starlypancacke.app.di.authModule
import com.binkos.starlypancacke.app.di.splashModule
import com.binkos.starlypancacke.data.di.preferencesModule
import com.binkos.starlypancacke.data.di.repositoryModule
import com.binkos.starlypancacke.domain.di.interactionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    splashModule,
                    appModule,
                    interactionModule,
                    repositoryModule,
                    authModule,
                    preferencesModule
                )
            )
        }
    }
}
