package com.cobeisfresh.template.ui.auth.head

import android.os.Bundle
import com.cobeisfresh.template.common.extensions.setLaunchScreen
import com.cobeisfresh.template.di.AUTH_FEATURE
import com.cobeisfresh.template.di.authModule
import com.cobeisfresh.template.ui.auth.signin.SignInFragmentScreen
import com.cobeisfresh.template.ui.base.FlowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class AuthFlowFragment : FlowFragment() {

    private val navigatorHolder: NavigatorHolder by inject(named(AUTH_FEATURE))

    override fun viewReady() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadKoinModules(authModule)

            navigator.setLaunchScreen(SignInFragmentScreen())
        }
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

            super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        unloadKoinModules(authModule)
    }

    companion object {
        fun getNewInstance() = AuthFlowFragment()
    }
}