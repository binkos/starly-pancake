package com.binkos.starlypancacke.app.ui.admin

import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AdminFlowScreen(private val adminId: String) :
    FragmentScreen(fragmentCreator = Creator { AdminFlowFragment.getInstance(adminId) })

class AdminOrganizationsScreen(private val adminId: String) :
    FragmentScreen(fragmentCreator = Creator { AdminOrganizationsFragment.getInstance(adminId) })