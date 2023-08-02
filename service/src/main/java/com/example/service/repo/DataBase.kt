package com.example.service.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.google.gson.annotations.SerializedName

//3 database的創建
@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
abstract class UatShopDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private const val Uat_DB_NAME = "uatshop.db"

        @Volatile
        private var instance: UatShopDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UatShopDatabase? {
            return instance ?: Room.databaseBuilder(
                context,
                UatShopDatabase::class.java,
                Uat_DB_NAME
            ).build().also {
                instance = it
            }
        }
    }

}


//1、entity的定义
@Entity(tableName = "tb_user")
data class UserInfo(
    @PrimaryKey
    val idd: Int, //主键
    val id: Int, //用户id
    @SerializedName("is_bind_phone")
    val isBindPhone: Boolean?,
    val logo_url: String?, //用户头像
    val token: String?,
    val username: String? //用户名
)

//2 Dao層的定義
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: UserInfo)

    @Delete
    fun deleteUser(user: UserInfo)

    @Query("select * from tb_user where id=0")
    fun queryLiveUser(): LiveData<UserInfo>

    @Query("select * from tb_user where id=0")
    fun queryUser(): UserInfo?

}