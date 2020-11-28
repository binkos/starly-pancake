package com.binkos.starlypancacke.app.ui.main

import android.os.Bundle
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.setLaunchScreen
import com.binkos.starlypancacke.app.di.AUTH_FEATURE
import com.binkos.starlypancacke.app.di.MAIN_FLOW_FEATURE
import com.binkos.starlypancacke.app.di.authModule
import com.binkos.starlypancacke.app.di.mainModule
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class MainFlowFragment : FlowFragment() {

    private val navigatorHolder: NavigatorHolder by inject(named(MAIN_FLOW_FEATURE))


    override fun getLayout(): Int {
        return R.layout.fragment_main_flow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadKoinModules(mainModule)
            navigator.setLaunchScreen(MapFragmentScreen())
        }
    }

    override fun viewReady() {

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

        unloadKoinModules(mainModule)
    }

    companion object {
        fun getInstance() = MainFlowFragment()
    }
}