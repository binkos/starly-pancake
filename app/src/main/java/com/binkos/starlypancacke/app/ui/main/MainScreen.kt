package com.binkos.starlypancacke.app.ui.main

import com.binkos.starlypancacke.app.ui.main.map.MapFragment
import com.binkos.starlypancacke.app.ui.main.places.OrganizationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MapFragmentScreen : FragmentScreen(fragmentCreator = { MapFragment() })

class MainFlowScreen : FragmentScreen(fragmentCreator = { MainFlowFragment.getInstance() })

class OrganizationFragmentScreen(private val name: String) :
    FragmentScreen(fragmentCreator = { OrganizationFragment.newInstance(name) })