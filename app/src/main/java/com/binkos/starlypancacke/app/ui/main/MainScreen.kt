package com.binkos.starlypancacke.app.ui.main

import com.binkos.starlypancacke.app.ui.main.map.MapFragment
import com.binkos.starlypancacke.app.ui.main.places.OrganizationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MapFragmentScreen(name: String? = null) :
    FragmentScreen(fragmentCreator = { MapFragment.getInstance(name) })

class MainFlowScreen(name: String? = null) :
    FragmentScreen(fragmentCreator = { MainFlowFragment.getInstance(name) })

class OrganizationFragmentScreen(name: String, isAdmin: Boolean = false) :
    FragmentScreen(fragmentCreator = { OrganizationFragment.newInstance(name, isAdmin) })