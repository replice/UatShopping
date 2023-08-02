package com.example.common.ktx

import com.example.common.model.DataResult
import retrofit2.Call
import retrofit2.await
import java.lang.RuntimeException


/**
 * Retrofit的call执行异步，并转化为liveData可观察结果
 */
suspend fun <T:Any> Call<T>.serverData(): DataResult<T>{
    var result:DataResult<T> = DataResult.Loading
    runCatching {
        this.await()
    }.onFailure {
        result = DataResult.Error(RuntimeException(it))
    }.onSuccess {
        result = DataResult.Success(it)
    }
    return result
}