package com.example.service.net

import androidx.annotation.Keep


/*
* 基础的网络返回数据
* @Keep在混淆的时候会保留@Keep类的信息
* */
@Keep
data class BaseResponse(
    val code: Int,  //响应码
    val data: Any?, //响应数据内容
    val message: String? //响应数据的结果描述
){
    companion object{
        const val SERVER_CODE_FAILED = 0 //接口请求响应业务处理 失败
        const val SERVER_CODE_SUCCESS = 1001 //接口请求响应业务处理 成功
        const val SERVER_CODE_SUCCESS1 = 1 //接口请求响应业务处理 成功
    }
}