package com.binkos.starlypancacke.app.app

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator

class AppMainNavigator(activity: FragmentActivity, containerId: Int) :
    AppNavigator(activity, containerId) {

    override fun applyCommand(command: Command) {
        when (command) {
            is CloseApplication -> activity.finish()
            else -> super.applyCommand(command)
        }
    }
}

class CloseApplication : Command