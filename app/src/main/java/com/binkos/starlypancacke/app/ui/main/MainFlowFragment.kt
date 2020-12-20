package com.binkos.starlypancacke.app.ui.main

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.setLaunchScreen
import com.binkos.starlypancacke.app.common.extensions.tryToGetStringOrNull
import com.binkos.starlypancacke.app.di.MAIN_FLOW_FEATURE
import com.binkos.starlypancacke.app.di.mainModule
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import kotlinx.android.synthetic.main.fragment_main_flow.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class MainFlowFragment : FlowFragment() {

    private val navigatorHolder: NavigatorHolder by inject(named(MAIN_FLOW_FEATURE))
    private val vm: MainMapViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_main_flow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadKoinModules(mainModule)
            navigator.setLaunchScreen(MapFragmentScreen(tryToGetStringOrNull(ORGANIZATION_NAME_KEY)))
        }
    }

    override fun viewReady() {
        mainSideNavigationView.menu.getItem(0).isChecked = true
        mainSideNavigationView.menu.getItem(1).setOnMenuItemClickListener {
            vm.logout()
            true
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

        unloadKoinModules(mainModule)
    }

    fun onClick() {
        if (mainFlowDrawer.isDrawerOpen(GravityCompat.START)) {
            mainFlowDrawer.closeDrawer(GravityCompat.START)
        } else {
            mainFlowDrawer.openDrawer(GravityCompat.START)
        }
    }

    companion object {
        private const val ORGANIZATION_NAME_KEY = "ORGANIZATION_NAME"

        fun getInstance(name: String?) = MainFlowFragment().apply {
            arguments = Bundle()
                .apply {
                    putString(ORGANIZATION_NAME_KEY, name)
                }
        }
    }
}