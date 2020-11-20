package com.cobeisfresh.template.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cobeisfresh.template.routing.app.AppRouter
import com.example.domain.usecase.GetAuthorizeUseCase

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
}