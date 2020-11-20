package com.cobeisfresh.template.routing.app

import com.cobeisfresh.template.ui.auth.head.AuthFlowFragmentScreen
import com.cobeisfresh.template.ui.splash.SplashScreen
import com.github.terrakok.cicerone.Router

class AppRouter : Router() {

    fun launch() {
        newRootScreen(SplashScreen())
    }

    fun toAuthFlow() {
        newRootScreen(AuthFlowFragmentScreen())
    }
}