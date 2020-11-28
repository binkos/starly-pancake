package com.binkos.starlypancacke.app.app

import com.binkos.starlypancacke.app.ui.auth.head.AuthFlowFragmentScreen
import com.binkos.starlypancacke.app.ui.main.MainFlowScreen
import com.binkos.starlypancacke.app.ui.splash.SplashScreen
import com.github.terrakok.cicerone.Router

class AppRouter : Router() {

    fun launch() {
        newRootScreen(SplashScreen())
    }

    fun toAuthFlow() {
        newRootScreen(AuthFlowFragmentScreen())
    }

    fun toMainFlow() {
        newRootScreen(MainFlowScreen())
    }
}