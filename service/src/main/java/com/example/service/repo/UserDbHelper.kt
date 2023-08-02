package com.example.service.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object UserDbHelper {

    fun getLiveUserInfo(context: Context) =
        UatShopDatabase.getInstance(context)?.userDao()?.queryLiveUser()


    fun getUserInfo(context: Context) =
        UatShopDatabase.getInstance(context)?.userDao()?.queryUser()

    //删除用户信息
    fun deleteUserInfo(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            getUserInfo(context)?.let { info ->
                UatShopDatabase.getInstance(context)?.userDao()?.deleteUser(info)
            }

        }
    }

    /*
    新增用戶数据到数据表
     */
    fun insertUserInfo(context: Context, user: UserInfo) {
        GlobalScope.launch(Dispatchers.IO) {
            UatShopDatabase.getInstance(context)?.userDao()?.insert(user)
        }
    }
}
