package com.binkos.starlypancacke.app.ui.admin

import android.os.Bundle
import androidx.core.os.bundleOf
import com.binkos.starlypancacke.app.common.extensions.setLaunchScreen
import com.binkos.starlypancacke.app.common.extensions.tryToGetString
import com.binkos.starlypancacke.app.di.ADMIN_FLOW_FEATURE
import com.binkos.starlypancacke.app.di.adminModule
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class AdminFlowFragment : FlowFragment() {

    private val navigatorHolder: NavigatorHolder by inject(named(ADMIN_FLOW_FEATURE))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadKoinModules(adminModule)
            navigator.setLaunchScreen(AdminOrganizationsScreen(tryToGetString(ADMIN_ID_KEY)))
        }
    }

    override fun viewReady() {}

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

        unloadKoinModules(adminModule)
    }

    companion object {
        private const val ADMIN_ID_KEY = "ADMIN_ID_KEY"

        fun getInstance(adminID: String) = AdminFlowFragment().apply {
            arguments = bundleOf(Pair(ADMIN_ID_KEY, adminID))
        }
    }
}