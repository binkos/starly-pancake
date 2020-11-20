package com.cobeisfresh.template.ui.auth.head

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cobeisfresh.template.routing.app.AppRouter
import com.cobeisfresh.template.ui.auth.head.AuthRouter
import com.cobeisfresh.template.ui.auth.signup.routing.SignUpFragmentScreen
import com.example.domain.usecase.SignInUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val featureRouter: AuthRouter,
    private val authUseCase: SignInUseCase,
    private val appRouter: AppRouter
) : ViewModel() {

    private val userAuthLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var liveData: LiveData<Boolean> = userAuthLiveData

    fun authorize(email: String) {
        viewModelScope.launch {
            authUseCase.signIn(email)
            userAuthLiveData.value = true
        }
    }

    fun toSignUp() {
        appRouter.navigateTo(SignUpFragmentScreen())
    }

    fun toMainFlow() {
        featureRouter.navigateTo(SignUpFragmentScreen())
    }
}