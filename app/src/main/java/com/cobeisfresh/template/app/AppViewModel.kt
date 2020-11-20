package com.cobeisfresh.template.app

import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.routing.app.AppRouter

class AppViewModel(
    private val featureRouter: AppRouter
) : ViewModel() {

    fun launch() {
        featureRouter.launch()
    }

    fun finish() {
        featureRouter.exit()
    }
}