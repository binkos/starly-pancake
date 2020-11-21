package com.binkos.starlypancacke.app.ui.auth.head

import androidx.lifecycle.*
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.app.ui.auth.signin.SignInFragmentScreen
import com.binkos.starlypancacke.app.ui.auth.signup.routing.SignUpFragmentScreen
import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.FailureReason
import com.binkos.starlypancacke.domain.model.OnFailure
import com.binkos.starlypancacke.domain.usecase.SignInUseCase
import com.binkos.starlypancacke.domain.usecase.SignUpUseCase
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
                return@liveData
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