package com.example.common.utils

import com.blankj.utilcode.util.PathUtils
import com.tencent.mmkv.MMKV

object MySpUtils {

    //用腾讯的MMKV
    private val kv: MMKV by lazy {
        MMKV.defaultMMKV()
    }

    init {
        MMKV.initialize(PathUtils.getInternalAppFilesPath())
    }

    fun put(key: String, value: Any?) {
        when (value) {
            is String -> kv.encode(key, value)
            is Int -> kv.encode(key, value)
            is Boolean -> kv.encode(key, value)
            is Float -> kv.encode(key, value)
            is Long -> kv.encode(key, value)
            is Double -> kv.encode(key, value)
            else -> error("${value?.javaClass?.simpleName} Not Supported By SpUtils")
        }
    }

    fun getBoolean(key: String, defValue: Boolean = false): Boolean = kv.getBoolean(key, defValue)

    fun getBytes(key: String, defValue: ByteArray? = null):ByteArray = kv.getBytes(key, defValue)

    fun getFloat(key: String, defValue: Float = 0f) = kv.getFloat(key, defValue)

    fun getInt(key: String, defValue: Int = 0) = kv.getInt(key, defValue)

    fun getLong(key: String, defValue: Long = 0L) = kv.getLong(key, defValue)

    fun getString(key: String, defValue: String? = null) = kv.getString(key, defValue)

    fun remove(key: String) = kv.remove(key)

    fun removeValue(key: String) = kv.removeValueForKey(key)
}