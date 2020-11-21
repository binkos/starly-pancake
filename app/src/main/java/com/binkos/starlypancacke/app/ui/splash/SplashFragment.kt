package com.binkos.starlypancacke.app.ui.splash

import android.widget.Toast
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun viewReady() {

        splashViewModel
            .launch()
            .observe(viewLifecycleOwner) {
                when (it) {
                    true -> {
                        Toast.makeText(requireContext(), "Hello", Toast.LENGTH_LONG).show()
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