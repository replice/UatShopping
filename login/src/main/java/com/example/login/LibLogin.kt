package com.example.login

import com.example.common.network.KtRetrofit
import com.example.common.utils.getBaseHost
import com.example.login.net.LoginService
import com.example.login.repo.ILoginResource
import com.example.login.repo.LoginManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
* koin注解管理类
* */
val moduleLogin: Module = module {

    //service retrofit
    //single声明单例对象
    // single {
    //     KtRetrofit.initConfig("http://yapi.54yct.com/mock/24/") //baseurl
    //         .getService(LoginService::class.java)
    // }

    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(LoginService::class.java)
    }

    //repo ILoginResource
    single { LoginManager(get()) } bind ILoginResource::class

    //viewModel
    viewModel { LoginViewModel(get()) }
}