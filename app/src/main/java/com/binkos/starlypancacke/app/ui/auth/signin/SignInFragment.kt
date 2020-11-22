package com.binkos.starlypancacke.app.ui.auth.signin

import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.onClick
import com.binkos.starlypancacke.app.ui.auth.head.AuthViewModel
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.domain.model.OnFailure
import com.binkos.starlypancacke.domain.model.OnSuccess
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignInFragment : BaseFragment() {

    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun viewReady() {

        authViewModel.liveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is OnSuccess -> authViewModel.toMainFlow()
                is OnFailure -> showError(result.reason.error, fragmentContainer)
            }
        })

        signInButton.onClick {
            authViewModel.authorize(
                loginInputEditText.text.toString().trim(),
                passwordInputEditText.text.toString().trim()
            )
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