package com.cobeisfresh.template.ui.splash

import android.widget.Toast
import com.cobeisfresh.template.R
import com.cobeisfresh.template.ui.base.BaseFragment
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

    companion object {
        fun getInstance() = SplashFragment()
    }
}