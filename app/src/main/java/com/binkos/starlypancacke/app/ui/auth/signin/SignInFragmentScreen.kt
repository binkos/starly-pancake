package com.binkos.starlypancacke.app.ui.auth.signin

import com.github.terrakok.cicerone.androidx.FragmentScreen

class SignInFragmentScreen : FragmentScreen(fragmentCreator = { SignInFragment.getNewInstance() })