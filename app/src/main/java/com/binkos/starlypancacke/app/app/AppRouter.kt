package com.binkos.starlypancacke.app.app

import com.binkos.starlypancacke.app.ui.admin.AdminFlowScreen
import com.binkos.starlypancacke.app.ui.auth.head.AuthFlowFragmentScreen
import com.binkos.starlypancacke.app.ui.main.MainFlowScreen
import com.binkos.starlypancacke.app.ui.splash.SplashScreen
import com.github.terrakok.cicerone.Router

class AppRouter : Router() {

    fun launch() {
        newRootScreen(SplashScreen())
    }

    fun toAuthFlow() {
        replaceScreen(AuthFlowFragmentScreen())
    }

    fun toMainFlow(name: String? = null) {
        replaceScreen(MainFlowScreen(name))
    }

    fun closeApp() {
        executeCommands(CloseApplication())
    }

    fun toAdmin(id: String) {
        newRootChain(AdminFlowScreen(adminId = id))
    }
}