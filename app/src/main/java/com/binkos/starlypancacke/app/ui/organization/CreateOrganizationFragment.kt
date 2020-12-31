package com.binkos.starlypancacke.app.ui.organization

import android.os.Bundle
import androidx.core.os.bundleOf
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.onClick
import com.binkos.starlypancacke.app.di.createOrganizationModule
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.domain.model.Organization
import kotlinx.android.synthetic.main.fragment_create_organization.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class CreateOrganizationFragment : BaseFragment() {

    private val vm: CreateOrganizationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(createOrganizationModule)
    }

    override fun viewReady() {
        createOrganizationToolbar.setNavigationOnClickListener {
            vm.exit()
        }

        createOrganizationButton.onClick {
            vm.createOrganization(
                createOrganizationNameInputEditText.text.toString(),
                createOrganizationNDescriptionInputEditText.text.toString(),
                createOrganizationPhotoIconInputEditText.text.toString(),
                createOrganizationLinksInputEditText.text.toString(),
                createOrganizationLatitudeInputEditText.text.toString(),
                createOrganizationLongitudeInputEditText.text.toString()
            )
        }
    }

    override fun getLayout() = R.layout.fragment_create_organization

    override fun onBackPressed() {
        vm.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(createOrganizationModule)
    }

    companion object {
        private const val ORGANIZATION_KEY = "ORGANIZATION_KEY"

        fun getInstance(org: Organization): CreateOrganizationFragment {
            return CreateOrganizationFragment().apply {
                arguments = bundleOf(Pair(ORGANIZATION_KEY, org))
            }
        }
    }
}