package com.binkos.starlypancacke.app.ui.organization

import com.binkos.starlypancacke.domain.model.Organization
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CreateOrganizationFragmentScreen(private val organization: Organization) :
    FragmentScreen(fragmentCreator = Creator { CreateOrganizationFragment.getInstance(organization) })