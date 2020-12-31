package com.binkos.starlypancacke.app.ui.splash

import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.ui.base.FlowFragment
import com.binkos.starlypancacke.domain.model.AuthUserStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : FlowFragment() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun viewReady() {

        splashViewModel
            .launch()
            .observe(viewLifecycleOwner) {
                when (it) {
                    AuthUserStatus.ADMIN -> {
                        splashViewModel.toAdmin(it.data)
                    }
                    AuthUserStatus.USER -> {
                        splashViewModel.toMainFlow()
                    }
                    AuthUserStatus.NONE -> {
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