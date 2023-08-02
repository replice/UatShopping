package com.example.mine.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.example.common.ktx.serverData
import com.example.mine.net.MineService
import com.example.mine.net.UserInfoRsp
import com.example.service.net.onBizError
import com.example.service.net.onBizOK
import com.example.service.net.onFailure
import com.example.service.net.onSuccess

class MineRepo(private val mineService: MineService): IMineResource {

    private val _userInfoRsp = MutableLiveData<UserInfoRsp>()

    override val liveUserInfo: LiveData<UserInfoRsp> = _userInfoRsp

    override suspend fun getUserInfo(token: String?) {
        mineService.getUserInfo(token)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取用户信息失败 $code $message")
                }
                onBizOK<UserInfoRsp> { code, data, message ->
                    _userInfoRsp.value = data
                    LogUtils.i("获取用户信息成功 $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("获取用户信息接口异常 ${it.message}")
            }

    }


}