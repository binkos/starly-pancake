package com.cobeisfresh.template.ui.auth.head

import androidx.lifecycle.*
import com.cobeisfresh.template.routing.app.AppRouter
import com.cobeisfresh.template.ui.auth.signin.SignInFragmentScreen
import com.cobeisfresh.template.ui.auth.signup.routing.SignUpFragmentScreen
import com.example.domain.model.AuthState
import com.example.domain.model.FailureReason
import com.example.domain.model.OnFailure
import com.example.domain.usecase.SignInUseCase
import com.example.domain.usecase.SignUpUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val featureRouter: AuthRouter,
    private val authUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
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

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): LiveData<AuthState> {
        return liveData {
            if (password != confirmPassword) {
                emit(OnFailure(FailureReason.INVALID_PASSWORD))
            }

            val result = withContext(viewModelScope.coroutineContext) {
                signUpUseCase.signUp(email, password)
            }

            emit(result)
        }
    }

    fun toSignUp() {
        featureRouter.navigateTo(SignUpFragmentScreen(), false)
    }

    fun toMainFlow() {
        appRouter.launch()
    }

    fun back() {
        featureRouter.backTo(SignInFragmentScreen())
    }

    fun finish(){
        featureRouter.exit()
    }
}