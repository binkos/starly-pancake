package com.cobeisfresh.template.ui.base

import com.cobeisfresh.template.R
import com.github.terrakok.cicerone.androidx.AppNavigator

abstract class FlowFragment : BaseFragment() {

    private val currentFragment: BaseFragment?
        get() = childFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment?

    override fun getLayout(): Int {
        return R.layout.fragment_flow
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }

    protected val navigator by lazy {
        AppNavigator(
            requireActivity(),
            fragmentManager = childFragmentManager,
            containerId = R.id.fragmentContainer
        )
    }
}