package com.example.mine.repo

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.example.mine.net.UserInfoRsp
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//1、entity的定义



@Parcelize
@Entity(tableName = "tv_userinforsp")
data class UserInfoRspData(
    @PrimaryKey
    val idd: Int, //主键
    var company: String?, //公司
    val desc: String?, //个人介绍
    val email: String?,
    @SerializedName("focus_it")
    val focusIt: String?,
    @SerializedName("follower_count")
    val followerCount: Int,
    @SerializedName("following_count")
    val followingCount: Int,
    val id: Int,
    val job: String?, //职业
    @SerializedName("logo_url")
    val logoUrl: String?, //头像url
    val mobi: String?, //手机号
    @SerializedName("real_name")
    val realName: String?, //真实姓名
    val username: String?, //用户名
    @SerializedName("work_years")
    val workYears: String? //工作时间
): Parcelable

@Dao
interface UserInfoRspDao{

    //增
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfoRsp(userInfoRsp: UserInfoRsp)

    //改
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUserInfoRsp(userInfoRsp: UserInfoRsp)

    //删
    @Delete
    fun deleteUserInfoRsp(userInfoRsp: UserInfoRsp)

    //查，查询数据表有可能为空
    @Query("select * from tv_userinforsp where idd = :idd")
    fun queryLiveUserInfoRsp(idd: Int = 0): LiveData<UserInfoRsp>

    @Query("select * from tv_userinforsp where idd = :idd")
    fun queryUserInfoRsp(idd: Int = 0): UserInfoRsp

}
