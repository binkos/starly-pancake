package com.binkos.starlypancacke.app.ui.auth.head

import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.Router

class AuthRouter : Router(){

    fun back(){
        executeCommands(Back())
    }
}