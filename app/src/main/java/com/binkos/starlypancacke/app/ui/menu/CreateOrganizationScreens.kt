package com.binkos.starlypancacke.app.ui.menu

import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FoodFragmentScreen: FragmentScreen(fragmentCreator = Creator { FoodFragment() })