package com.binkos.starlypancacke.app.core

import androidx.lifecycle.ViewModel
import com.binkos.starlypancacke.app.app.AppRouter

class AppViewModel(
    private val appRouter: AppRouter
) : ViewModel() {

    fun launch() {
        appRouter.launch()
    }

    fun finish() {
        appRouter.exit()
    }
}