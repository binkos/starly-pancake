package com.binkos.starlypancacke.app.ui.organization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binkos.starlypancacke.app.ui.admin.AdminRouter
import com.binkos.starlypancacke.domain.model.Organization
import com.binkos.starlypancacke.domain.usecase.CreateOrganizationsUseCase
import com.binkos.starlypancacke.domain.usecase.GetAdminUseCase
import kotlinx.coroutines.launch

class CreateOrganizationViewModel(
    private val featureRouter: AdminRouter,
    private val getAdminIdUseCase: GetAdminUseCase,
    private val createOrganizationUseCase: CreateOrganizationsUseCase
) : ViewModel() {

    fun createOrganization(
        name: String,
        description: String,
        photo: String,
        photos: String,
        lat: String,
        lng: String
    ) {
        if (verifyInfo(name, description, photo, photos, lat, lng)) {
            viewModelScope.launch {
                getAdminIdUseCase.execute()?.let {
                    val organization = Organization(
                        name,
                        photo,
                        photos.split("/n"),
                        description,
                        emptyList(),
                        lat.toDouble(),
                        lng.toDouble(),
                        it
                    )
                    createOrganizationUseCase.execute(organization)
                }
            }
        }
    }

    fun exit() {
        featureRouter.exit()
    }

    private fun verifyInfo(
        name: String,
        description: String,
        photo: String = "",
        photos: String = "",
        lat: String,
        lng: String
    ): Boolean {
        return name.isNotEmpty() && description.isNotEmpty() && lat.isNotEmpty() && lng.isNotEmpty()
    }
}