package com.binkos.starlypancacke.app.core

import android.content.Intent
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.domain.model.AuthUserStatus
import com.binkos.starlypancacke.domain.usecase.GetAuthorizeUseCase
import com.binkos.starlypancacke.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.charset.StandardCharsets

private const val ORGANIZATION_PATH_KEY = "organization"
private const val NAME_PATH_KEY = "name"
private const val EMAIL_PATH_KEY = "email"
private const val ADMIN_PATH_KEY = "admin"

class AppViewModel(
    private val appRouter: AppRouter,
    private val getAuthorizeUseCase: GetAuthorizeUseCase,
    private val signInUseCase: SignInUseCase
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
                    ADMIN_PATH_KEY -> {
                        val adminEmail = intent.data?.getQueryParameter(EMAIL_PATH_KEY)!!
                        val data: ByteArray = Base64.decode(adminEmail, Base64.DEFAULT)
                        val decodedText = String(data, StandardCharsets.UTF_8)
                        signInUseCase.saveAdmin(decodedText)
                        toAdmin(decodedText)
                    }
                    null -> handleNormalFlow(isRestart)
                }
            }
        }

        isAppRunning = true
    }

    private suspend fun handleNormalFlow(isRestart: Boolean) {
        if (!isRestart) {
            withContext(Dispatchers.Main) {
                when (val status = getAuthorizeUseCase.isUserAuthorized()) {
                    AuthUserStatus.USER -> appRouter.toMainFlow()
                    AuthUserStatus.ADMIN -> appRouter.toAdmin(status.data)
                    AuthUserStatus.NONE -> appRouter.launch()
                }
            }
        }
    }

    fun onNewIntent(intent: Intent?) {
        if (isAppRunning) {
            handleIntent(intent, true)
        }
    }

    fun finish() {
        appRouter.closeApp()
    }

    private fun toAdmin(id: String) {
        appRouter.toAdmin(id)

    }

    private fun toOrganization(name: String) {
        appRouter.toMainFlow(name)
    }
}