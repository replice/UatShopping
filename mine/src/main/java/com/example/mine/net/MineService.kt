package com.example.mine.net


import com.example.mine.repo.UserInfoRspData
import com.example.service.net.BaseResponse
import com.example.service.repo.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MineService {

    /**
     * 用户详情的获取
     */
    @GET("/member/userinfo")
    fun getUserInfo(@Header("token") token: String?): Call<BaseResponse>


}