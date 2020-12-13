package com.binkos.starlypancacke.app.core

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.domain.usecase.GetAuthorizeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ORGANIZATION_PATH_KEY = "organization"
private const val NAME_PATH_KEY = "name"
private const val ADMIN_PATH_KEY = "admin"

class AppViewModel(
    private val appRouter: AppRouter,
    private val getAuthorizeUseCase: GetAuthorizeUseCase
) : ViewModel() {

    var isAppRunning: Boolean = false

    fun handleIntent(intent: Intent?, isRestart: Boolean = false) {
        val action = intent?.action ?: ""

        viewModelScope.launch {
            if (action == Intent.ACTION_MAIN) {
                handleNormalFlow(isRestart)
            } else {
                when (intent?.data?.lastPathSegment) {
                    ORGANIZATION_PATH_KEY -> {
                        val orgName = intent.data?.getQueryParameter(NAME_PATH_KEY)!!
                        toOrganization(orgName)
                    }
                    ADMIN_PATH_KEY -> toAdmin()
                    null -> handleNormalFlow(isRestart)
                }
            }
        }

        isAppRunning = true
    }

    private suspend fun handleNormalFlow(isRestart: Boolean) {
        if (!isRestart) {
            withContext(Dispatchers.Main) {
                if (getAuthorizeUseCase.isUserAuthorized()) {
                    appRouter.toMainFlow()
                } else {
                    launch()
                }
            }
        }
    }

    fun onNewIntent(intent: Intent?) {
        if (isAppRunning) {
            handleIntent(intent, true)
        }
    }

    private fun launch() {
        appRouter.launch()
    }

    fun finish() {
        appRouter.closeApp()
    }

    private fun toAdmin() {

    }

    private fun toOrganization(name: String) {
        appRouter.toMainFlow(name)
    }
}