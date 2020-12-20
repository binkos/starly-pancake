package com.binkos.starlypancacke.app.ui.admin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.app.ui.organization.CreateOrganizationFragment
import com.binkos.starlypancacke.app.ui.organization.CreateOrganizationFragmentScreen
import com.binkos.starlypancacke.domain.model.*
import com.binkos.starlypancacke.domain.usecase.GetOrganizationsUseCase
import com.binkos.starlypancacke.domain.usecase.LogoutUseCase
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import com.binkos.starlypancacke.domain.model.Result as DomainReuslt

class AdminViewModel(
    private val adminId: String,
    private val featureRouter: AdminRouter,
    private val appRouter: AppRouter,
    private val getOrganizationsUseCase: GetOrganizationsUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val organizationsLiveData: MutableLiveData<DomainReuslt<List<Organization>>> =
        MutableLiveData()
    val orgLiveData: LiveData<DomainReuslt<List<Organization>>> = organizationsLiveData

    fun launch() {
        viewModelScope.launch(Dispatchers.Main) {
            organizationsLiveData.postValue(Progress())
            getOrganizationsUseCase
                .execute()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e(this@AdminViewModel::class.java.toString(), it.toString())
                    organizationsLiveData.postValue(Failure(ApplicationError(it.toString())))
                }
                .collect {
                    organizationsLiveData.postValue(Success(it))
                }
        }
    }

    fun exit() {
        featureRouter.exit()
    }

    fun toCreateOrganization() {
        featureRouter.navigateTo(FragmentScreen { CreateOrganizationFragment() })
    }

    fun toChangeOrganization(organization: Organization) {
        featureRouter.navigateTo(CreateOrganizationFragmentScreen(organization))
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
            appRouter.toAuthFlow()
        }
    }
}