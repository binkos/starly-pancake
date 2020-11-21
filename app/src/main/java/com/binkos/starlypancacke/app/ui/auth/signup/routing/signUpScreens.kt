package com.binkos.starlypancacke.app.ui.auth.signup.routing

import com.binkos.starlypancacke.app.ui.auth.signup.SignUpFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SignUpFragmentScreen() : FragmentScreen(fragmentCreator = { SignUpFragment.getNewInstance() })