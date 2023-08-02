package com.example.common.network

import androidx.core.net.toUri
import com.example.common.BuildConfig
import com.example.common.utils.MySpUtils
import com.example.common.utils.getBaseHost
import okhttp3.Interceptor
import okhttp3.Response

class HostInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val oldHost = originRequest.url.host
        val oldUrlStr = originRequest.url.toString()
        //调试使用
        val newHost = getBaseHost().toUri().host ?: oldHost
        val newUrlStr = if (newHost == oldHost) {
            oldUrlStr
        } else oldUrlStr.replace(oldHost, newHost)

        val newBuilder = originRequest.newBuilder()
        newBuilder.url(newUrlStr)
        return chain.proceed(newBuilder.build())
    }

}


