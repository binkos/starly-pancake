package com.cobeisfresh.template.app

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.EMPTY_STRING
import com.cobeisfresh.template.common.extensions.gone
import com.cobeisfresh.template.common.extensions.showFragment
import com.cobeisfresh.template.common.extensions.showSnackBar
import com.cobeisfresh.template.common.extensions.visible
import com.cobeisfresh.template.di.APP_MODULE
import com.cobeisfresh.template.di.appModule
import com.cobeisfresh.template.routing.app.AppMainNavigator
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named

class AppActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by inject()
    private val featureNavigatorHolder: NavigatorHolder by inject(named(APP_MODULE))
    private lateinit var appMainNavigator: AppMainNavigator

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

    fun showError(@StringRes errorMessage: Int, rootView: View) =
        showSnackBar(errorMessage, rootView)

    fun showError(errorMessage: String?, rootView: View) =
        showSnackBar(errorMessage ?: EMPTY_STRING, rootView)

    fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    fun hideLoading(progressBar: ProgressBar) = progressBar.gone()
}
