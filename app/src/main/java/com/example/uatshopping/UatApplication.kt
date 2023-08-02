package com.example.uatshopping
import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.BaseApplication
import com.example.login.moduleLogin
import com.example.service.assistant.AssistantApp
import com.example.service.moduleService
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

class UatApplication : BaseApplication() {

    private val modules = arrayListOf<Module>(
        moduleLogin, moduleService
//        moduleService, moduleHome, moduleLogin, moduleMine, moduleStudy, moduleCourse
    )

    override fun initConfig() {
        super.initConfig()

        //添加common模块之外的其他模块，组件的koin的modules
        loadKoinModules(modules)

        //doKit的初始化配置
        AssistantApp.initConfig(this)

        //初始化Arouter框架
        ARouter.init(this)
    }

}