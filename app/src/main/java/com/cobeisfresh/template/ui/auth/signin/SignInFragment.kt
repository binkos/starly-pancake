package com.cobeisfresh.template.ui.auth.signin

import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.onClick
import com.cobeisfresh.template.ui.auth.head.AuthViewModel
import com.cobeisfresh.template.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignInFragment : BaseFragment() {

    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun viewReady() {

        authViewModel.liveData.observe(viewLifecycleOwner, { result ->
            if (result) {
                authViewModel.toMainFlow()
            }
        })

        signInButton.onClick {
            authViewModel.authorize(loginInputEditText.text.toString())
        }

        signUpButton.onClick {
            authViewModel.toSignUp()
        }
    }

    override fun getLayout(): Int = R.layout.fragment_login

    override fun onBackPressed() {
        authViewModel.finish()
    }

    companion object {
        fun getNewInstance() = SignInFragment()
    }
}