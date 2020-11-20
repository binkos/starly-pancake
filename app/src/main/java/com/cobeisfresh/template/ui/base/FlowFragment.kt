package com.cobeisfresh.template.ui.base

import com.cobeisfresh.template.R
import com.github.terrakok.cicerone.androidx.AppNavigator

abstract class FlowFragment : BaseFragment() {

    open lateinit var scope: String

    override fun getLayout(): Int {
        return R.layout.fragment_flow
    }

    protected val navigator by lazy {
        AppNavigator(
            requireActivity(),
            fragmentManager = childFragmentManager,
            containerId = R.id.fragmentContainer
        )
    }
}