package com.cobeisfresh.template.ui.auth.signup.routing

import com.cobeisfresh.template.ui.auth.signup.SignUpFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SignUpFragmentScreen() : FragmentScreen(fragmentCreator = { SignUpFragment.getNewInstance() })