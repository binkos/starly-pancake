package com.binkos.starlypancacke.app.ui.main.places

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binkos.starlypancacke.app.common.extensions.launch
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.usecase.GetOrganizationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class OrganizationViewModel(
    private val getOrganizationsUseCase:GetOrganizationsUseCase
) : ViewModel() {

    private val organizationsLiveData: MutableLiveData<List<Organization>> = MutableLiveData()
    val orgLiveData: LiveData<List<Organization>> = organizationsLiveData

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
}