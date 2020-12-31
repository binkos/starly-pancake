package com.binkos.starlypancacke.app.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.app.common.extensions.launch
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.usecase.GetOrganizationsUseCase
import com.binkos.starlypancacke.domain.usecase.LogoutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class MainMapViewModel(
    private val appRouter: AppRouter,
    private val featureRouter: MainFlowRouter,
    private val getOrganizationsUseCase: GetOrganizationsUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val organizationsLiveData: MutableLiveData<List<Organization>> = MutableLiveData()
    val orgLiveData: LiveData<List<Organization>> = organizationsLiveData

    fun close() {
        appRouter.closeApp()
    }

    fun launchMapFragment() {
        launch() {
            getOrganizationsUseCase
                .execute()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e(this::class.java.toString(), it.message.toString())
                }
                .collect {
                    organizationsLiveData.value = it
                }
        }
    }

    fun launchOrgSearch(name: String) {
        launch {
            getOrganizationsUseCase
                .find(name)
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e(this::class.java.toString(), it.message.toString())
                }
                .collect {
                    organizationsLiveData.value = it
                }
        }
    }

    fun toOrganization(name: String) {
        featureRouter.navigateTo(OrganizationFragmentScreen(name))
    }

    fun logout() {
        launch {
            logoutUseCase.logout()
            appRouter.toAuthFlow()
        }
    }

    fun toTermsConditions(){
        appRouter.toTermsConditions()
    }

    fun toPrivacyPolicy(){
        appRouter.toPrivacyPolicy()
    }
}