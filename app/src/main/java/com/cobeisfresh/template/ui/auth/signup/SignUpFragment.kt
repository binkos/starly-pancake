package com.cobeisfresh.template.ui.auth.signup

import android.widget.Toast
import com.cobeisfresh.template.R
import com.cobeisfresh.template.ui.base.BaseFragment

class SignUpFragment : BaseFragment() {

    override fun viewReady() {
        Toast.makeText(requireContext(), "TOAST", Toast.LENGTH_LONG).show()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_onboarding
    }

    companion object{
        fun getNewInstance() = SignUpFragment()
    }
}