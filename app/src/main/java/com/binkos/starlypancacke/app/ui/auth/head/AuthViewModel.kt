package com.binkos.starlypancacke.app.ui.auth.head

import android.util.Log
import androidx.lifecycle.*
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.app.common.extensions.launch
import com.binkos.starlypancacke.app.ui.auth.signin.SignInFragmentScreen
import com.binkos.starlypancacke.app.ui.auth.signup.routing.SignUpFragmentScreen
import com.binkos.starlypancacke.domain.model.AuthState
import com.binkos.starlypancacke.domain.model.FailureReason
import com.binkos.starlypancacke.domain.model.OnFailure
import com.binkos.starlypancacke.domain.model.OnSuccess
import com.binkos.starlypancacke.domain.usecase.SignInUseCase
import com.binkos.starlypancacke.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val featureRouter: AuthRouter,
    private val authUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val appRouter: AppRouter
) : ViewModel() {

    private val userAuthLiveData: MutableLiveData<AuthState> = MutableLiveData()
    var liveData: LiveData<AuthState> = userAuthLiveData

    fun authorize(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            userAuthLiveData.value = OnFailure(FailureReason.INVALID_CREDENTIALS)
        }

        launch {
            authUseCase
                .signIn(email, password)
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e(this@AuthViewModel.toString(), it.message.toString())
                }
                .collect {
                    when (it) {
                        is OnSuccess -> {
                            authUseCase.save(email)
                            userAuthLiveData.value = it
                        }
                        is OnFailure -> {
                            userAuthLiveData.value = it
                            Log.e(this@AuthViewModel::class.java.toString(), it.reason.name)
                        }
                    }
                }
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

            if (email.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()
            ) {
                emit(OnFailure(FailureReason.INVALID_CREDENTIALS))
                return@liveData
            }

            val result =
                withContext(viewModelScope.coroutineContext) {
                    signUpUseCase.signUp(email, password)
                }

            emit(result)
        }
    }

    fun toSignUp() {
        featureRouter.navigateTo(SignUpFragmentScreen(), false)
    }

    fun toMainFlow() {
        appRouter.toMainFlow()
    }

    fun back() {
        featureRouter.backTo(SignInFragmentScreen())
    }

    fun finish() {
        featureRouter.exit()
    }
}