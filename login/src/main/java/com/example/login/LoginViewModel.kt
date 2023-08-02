package com.example.login

import android.view.View
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.example.common.base.BaseViewModel
import com.example.login.net.LoginReqBody
import com.example.login.repo.ILoginResource

class LoginViewModel(private val resource: ILoginResource): BaseViewModel() {

    /*
    * ObservableField只有在数据发生改变时UI才会收到通知，而LiveData不同，只要你postValue或者setValue，UI都会收到通知，不管数据有无变化
    * livedata还能感知activity的生命周期，在Activity不活动的时候不会触发
    * */
    val obMobile = ObservableField<String>() //手机号
    val obPassword = ObservableField<String>() //密码

    //返回的数据
    val liveRegisterRsp = resource.registerRsp
    val liveLoginRsp = resource.loginRsp

    //检查账号是否注册
    private fun checkRegister(mobile: String) = serverAwait {
        resource.checkRegister(mobile)
    }

    //登录请求
    internal fun login(){
        val account = obMobile.get() ?: return
        val password = obPassword.get() ?: return
        serverAwait {
            resource.requestLogin(LoginReqBody(account,password))
        }
    }

    /*
  * 登录按钮点击事件
  * 调用登录，两步
  * 1、判断手机号是否已经注册
  * 2、已经注册的，去调用登录接口
  * */
    fun goLogin(){
        val mobile = obMobile.get() ?: return
        checkRegister(mobile)
    }

    fun AA(view: View) {
        ToastUtils.showShort("静态点击方式")
    }



}