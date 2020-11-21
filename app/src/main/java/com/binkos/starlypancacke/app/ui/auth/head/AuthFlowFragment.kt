package com.binkos.starlypancacke.app.ui.auth.head

import android.os.Bundle
import com.binkos.starlypancacke.app.common.extensions.setLaunchScreen
import com.binkos.starlypancacke.app.di.AUTH_FEATURE
import com.binkos.starlypancacke.app.di.authModule
import com.binkos.starlypancacke.app.ui.auth.signin.SignInFragmentScreen
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class AuthFlowFragment : FlowFragment() {

    private val navigatorHolder: NavigatorHolder by inject(named(AUTH_FEATURE))

    val vm: AuthViewModel by viewModel()

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