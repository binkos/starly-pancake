package com.cobeisfresh.template.app

import android.app.Application
import com.cobeisfresh.template.di.appModule
import com.cobeisfresh.template.di.authModule
import com.cobeisfresh.template.di.splashModule
import com.example.data.di.networkingModule
import com.example.data.di.preferencesModule
import com.example.data.di.repositoryModule
import com.example.domain.di.interactionModule
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
                    networkingModule,
                    repositoryModule,
                    authModule,
                    preferencesModule
                )
            )
        }
    }
}
