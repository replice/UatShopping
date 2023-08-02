package com.example.login.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.example.common.ktx.serverData
import com.example.login.net.LoginReqBody
import com.example.login.net.LoginRsp
import com.example.login.net.LoginService
import com.example.login.net.RegisterRsp
import com.example.service.net.BaseResponse
import com.example.service.net.onBizError
import com.example.service.net.onBizOK
import com.example.service.net.onFailure
import com.example.service.net.onSuccess
import com.example.service.net.toEntity

class LoginManager(private val service: LoginService): ILoginResource {

    //官方推荐
    private val _registerRsp = MutableLiveData<RegisterRsp>()
    private val _loginRsp = MutableLiveData<LoginRsp>()

    override val registerRsp: LiveData<RegisterRsp> = _registerRsp

    override val loginRsp: LiveData<LoginRsp> = _loginRsp

    override suspend fun checkRegister(mobi: String) {
        service.isRegister(mobi)
            .serverData()
            .onSuccess{
                onBizError { code, message ->
                    LogUtils.w("是否注册 BizError $code,$message")
                }
                onBizOK<RegisterRsp> { code, data, message ->
                    _registerRsp.value = data
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("接口异常${it.message}")
            }

    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("登录接口 BizError $code,$message")
                }
                onBizOK<LoginRsp> { code, data, message ->
                    _loginRsp.value = data
                    //同步到room数据库 登录状态
                    // LogUtils.i("登录接口 BizOK $data")
                    return@onBizOK
                }
            }.onFailure {
                LogUtils.e("登录接口 接口异常 ${it.message}")
            }
    }



}