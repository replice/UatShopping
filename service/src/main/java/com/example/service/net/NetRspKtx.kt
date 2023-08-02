package com.example.service.net

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.example.common.model.DataResult
import com.example.common.model.succeeded
import com.google.gson.Gson
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 这里表示网络请求成功，并得到业务服务器的响应
 * 将BaseCniaoRsp的对象，转化为需要的对象类型，也就是将body.string转为entity
 * @return 返回需要类型对象，可能为null，如果json解析失败的话
 * 加了reified才可以T::class.java
 */
inline fun <reified T> BaseResponse.toEntity(): T? {
    if (this.data == null) {
        LogUtils.e("server Response Json Ok,But data==null,$code,$message")
        return null
    }
    return kotlin.runCatching {
        GsonUtils.fromJson(Gson().toJson(data), T::class.java)
    }.onFailure { e ->
        e.printStackTrace()
    }.getOrNull() //不为空
}


@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(action: R.() -> Unit): DataResult<R> {
    //契约类
    contract {
        callsInPlace(action, InvocationKind.AT_LEAST_ONCE)
    }
    if (succeeded) action.invoke((this as DataResult.Success).data)

    return this
}


//网络请求报错
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onFailure(action: (exception: Throwable) -> Unit): DataResult<R> {
    //契约类
    contract {
        callsInPlace(action, InvocationKind.AT_LEAST_ONCE)
    }
    if (this is DataResult.Error) action.invoke(exception)
    return this
}

/**
 * 接口成功，业务返回code == 1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseResponse.onBizOK(crossinline action: (code: Int, data: T?, message: String?) -> Unit): BaseResponse {
    contract {
        callsInPlace(action, InvocationKind.AT_LEAST_ONCE)
    }
    if (code == BaseResponse.SERVER_CODE_SUCCESS || code == BaseResponse.SERVER_CODE_SUCCESS1) {
        action.invoke(code, toEntity<T>(), message)
    }
    return this
}

/**
 * 接口成功，但是业务返回code不是1或1001的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun BaseResponse.onBizError(crossinline action: (code: Int, message: String?) -> Unit): BaseResponse {
    contract {
        callsInPlace(action, InvocationKind.AT_LEAST_ONCE)
    }
    if (code == BaseResponse.SERVER_CODE_FAILED) {
        action.invoke(code, message ?: "Error msg is null")
    }
    return this
}