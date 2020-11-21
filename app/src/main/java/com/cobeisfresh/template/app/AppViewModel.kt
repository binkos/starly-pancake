package com.cobeisfresh.template.app

import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.routing.app.AppRouter

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