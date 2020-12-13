package com.binkos.starlypancacke.app.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.domain.usecase.GetAuthorizeUseCase

class SplashViewModel(
    private val getAuthorizeUseCase: GetAuthorizeUseCase,
    private val appRouter: AppRouter
) : ViewModel() {

    fun launch(): LiveData<Boolean> {
        return liveData {
            emit(getAuthorizeUseCase.isUserAuthorized())
        }
    }

    fun toLogin() {
        appRouter.toAuthFlow()
    }

    fun finish(){
        appRouter.closeApp()
    }

    fun toMainFlow(){
        appRouter.toMainFlow()
    }
}