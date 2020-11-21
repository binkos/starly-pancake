package com.cobeisfresh.template.ui.auth.signup

import android.widget.Toast
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.onClick
import com.cobeisfresh.template.common.extensions.subscribe
import com.cobeisfresh.template.ui.auth.head.AuthViewModel
import com.cobeisfresh.template.ui.base.BaseFragment
import com.example.domain.model.OnFailure
import com.example.domain.model.OnSuccess
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpFragment : BaseFragment() {

    private val vm: AuthViewModel by sharedViewModel()

    override fun viewReady() {

        signUpButton.onClick {
            vm.signUp(
                loginInputEditText.text.toString(),
                passwordInputEditText.text.toString(),
                confirmPasswordInputEditText.text.toString()
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