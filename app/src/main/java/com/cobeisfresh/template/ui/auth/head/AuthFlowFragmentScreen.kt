package com.cobeisfresh.template.ui.auth.head

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AuthFlowFragmentScreen :
    FragmentScreen(fragmentCreator = { AuthFlowFragment.getNewInstance() })