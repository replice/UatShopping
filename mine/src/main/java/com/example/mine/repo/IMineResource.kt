package com.example.mine.repo

import androidx.lifecycle.LiveData

interface IMineResource {

    /**
     * 用户信息返回的数据类
     */
    val liveUserInfo: LiveData<UserInfoRspData>

    /**
     * 获取userInfo的api函数
     */
    suspend fun getUserInfo(token: String?)
}