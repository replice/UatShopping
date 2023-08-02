package com.example.login

import com.example.common.BaseApplication
import com.example.service.moduleService
import org.koin.core.context.loadKoinModules

class LoginApplication: BaseApplication() {

    override fun initConfig() {
        super.initConfig()
        loadKoinModules(moduleService)
        loadKoinModules(moduleLogin)
    }

}