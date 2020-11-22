package com.binkos.starlypancacke.app.ui.auth.signup

import android.widget.Toast
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.onClick
import com.binkos.starlypancacke.app.common.extensions.subscribe
import com.binkos.starlypancacke.app.ui.auth.head.AuthViewModel
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.domain.model.OnFailure
import com.binkos.starlypancacke.domain.model.OnSuccess
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpFragment : BaseFragment() {

    private val vm: AuthViewModel by sharedViewModel()

    override fun viewReady() {

        signUpButton.onClick {
            vm.signUp(
                loginInputEditText.text.toString().trim(),
                passwordInputEditText.text.toString().trim(),
                confirmPasswordInputEditText.text.toString().trim()
            )
                .subscribe(viewLifecycleOwner) {
                    when (it) {
                        is OnSuccess -> {
                            Toast.makeText(requireContext(), "User registered", Toast.LENGTH_SHORT)
                                .show()
                            vm.back()
                        }
                        is OnFailure -> {
                            Toast.makeText(requireContext(), it.reason.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_signup
    }

    override fun onBackPressed() {
        vm.back()
    }


    companion object {
        fun getNewInstance() = SignUpFragment()
    }
}