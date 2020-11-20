package com.example.data.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val PREFERENCES_DEV_KEY = "LQ35FRKC95TZV"

val preferencesModule = module {
    factory<SharedPreferences> {
        androidApplication().getSharedPreferences(PREFERENCES_DEV_KEY, Context.MODE_PRIVATE)
    }
}