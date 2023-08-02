package com.example.login.net

import androidx.annotation.Keep
import com.example.service.repo.UserInfo


@Keep
data class RegisterRsp(
    val is_register: Int = FLAG_UN_REGISTERED
){
    companion object{
        const val FLAG_IS_REGISTERED = 1 //已经注册
        const val FLAG_UN_REGISTERED = 0 //未注册
    }
}


/*
* 手机号和密码登录 接口响应
* */
//Room类型别名
typealias LoginRsp = UserInfo