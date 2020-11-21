package com.binkos.starlypancacke.app.core

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.EMPTY_STRING
import com.binkos.starlypancacke.app.common.extensions.gone
import com.binkos.starlypancacke.app.common.extensions.showSnackBar
import com.binkos.starlypancacke.app.common.extensions.visible
import com.binkos.starlypancacke.app.di.APP_MODULE
import com.binkos.starlypancacke.app.di.appModule
import com.binkos.starlypancacke.app.app.AppMainNavigator
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class AppActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by inject()
    private val featureNavigatorHolder: NavigatorHolder by inject(named(APP_MODULE))
    private lateinit var appMainNavigator: AppMainNavigator

    private val currentFlowFragment: FlowFragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as FlowFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(appModule)
        appMainNavigator = AppMainNavigator(this, R.id.fragmentContainer)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) appViewModel.launch()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        featureNavigatorHolder.setNavigator(appMainNavigator)
    }

    override fun onPause() {
        super.onPause()
        featureNavigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        unloadKoinModules(appModule)
        super.onDestroy()
    }

    override fun onBackPressed() {
        currentFlowFragment?.onBackPressed() ?: appViewModel.finish()
    }

    fun showError(@StringRes errorMessage: Int, rootView: View) =
        showSnackBar(errorMessage, rootView)

    fun showError(errorMessage: String?, rootView: View) =
        showSnackBar(errorMessage ?: EMPTY_STRING, rootView)

    fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    fun hideLoading(progressBar: ProgressBar) = progressBar.gone()
}
