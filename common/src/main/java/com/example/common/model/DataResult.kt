package com.example.common.model

import java.lang.Exception

sealed class DataResult<out T>{

    //成功状态
    data class Success<T>(val data: T): DataResult<T>()

    //错误，失败状态的时候
    data class Error(val exception: Exception) : DataResult<Nothing>()

    //加载数据中
    object Loading : DataResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 *返回结果如果是Success类，且data非null,才认为是成功的
 */
val DataResult<*>.succeeded: Boolean
    get() = this is DataResult.Success && data != null