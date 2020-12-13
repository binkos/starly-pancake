package com.binkos.starlypancacke.app.ui.splash

import android.widget.Toast
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : FlowFragment() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun viewReady() {

        splashViewModel
            .launch()
            .observe(viewLifecycleOwner) {
                when (it) {
                    true -> {
                        splashViewModel.toMainFlow()
                    }
                    false -> {
                        splashViewModel.toLogin()
                    }
                }
            }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_onboarding
    }

    override fun onBackPressed() {
        splashViewModel.finish()
    }

    companion object {
        fun getInstance() = SplashFragment()
    }
}