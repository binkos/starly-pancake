package com.binkos.starlypancacke.app.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.binkos.starlypancacke.app.app.AppRouter
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.usecase.GetOrganizationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMapViewModel(
    private val appRouter: AppRouter,
    private val featureRouter: MainFlowRouter,
    private val getOrganizationsUseCase: GetOrganizationsUseCase
) : ViewModel() {

    private val organizationsLiveData: MutableLiveData<List<Organization>> = MutableLiveData()
    val orgLiveData: LiveData<List<Organization>> = organizationsLiveData

    fun close() {
        appRouter.exit()
    }

    fun launchMapFragment() {
        viewModelScope.launch(Job() + Dispatchers.Main) {
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

    fun findOrganization(name: String): LiveData<Organization> {
        return liveData {
            withContext(Job() + Dispatchers.Main) {
                getOrganizationsUseCase
                    .getOrganization(name)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e(this::class.java.toString(), it.message.toString())
                    }
                    .collect {
                        emit(it)
                    }
            }
        }
    }

    fun toOrganization(name: String) {
        featureRouter.navigateTo(OrganizationFragmentScreen(name))
    }

    fun backToMap() {
        featureRouter.backTo(MapFragmentScreen())
    }
}