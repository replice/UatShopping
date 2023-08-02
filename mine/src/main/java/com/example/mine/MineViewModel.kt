package com.example.mine

import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.mine.repo.IMineResource
import com.example.service.repo.UserInfo

class MineViewModel(private val repo: IMineResource): BaseViewModel() {

    val liveUserInfo = repo.liveUserInfo

    /**
     * 获取用户信息
     */
    fun getUserInfo(token: String?) {
        serverAwait {
            repo.getUserInfo(token)
        }
    }


}