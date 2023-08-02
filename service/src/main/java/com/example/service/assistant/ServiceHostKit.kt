package com.example.service.assistant

import android.app.AlertDialog
import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.example.common.utils.HOST_DEV
import com.example.common.utils.HOST_PRODUCT
import com.example.common.utils.HOST_QA
import com.example.common.utils.getBaseHost
import com.example.common.utils.setBaseHost
import com.example.service.R

/**
 * 自定义的调试工具类，用于天界到dokit里切换host
 */
class ServiceHostKit: AbstractKit() {

    private val hostMap = mapOf(
        "开发环境Host" to HOST_DEV,
        "测试环境Host(无)" to HOST_QA,
        "线上正式Host(无)" to HOST_PRODUCT
    )

    private val hosts = hostMap.values.toTypedArray()
    private val names = hostMap.keys.toTypedArray()

    override val icon: Int = R.drawable.icon_server_host

    override val name: Int = R.string.str_server_host_dokit

    override fun onAppInit(context: Context?) {

    }

    override fun onClick(context: Context?) {
        val pos = hostMap.values.indexOf(getBaseHost()) % hosts.size
        //弹窗，用于显示跟选择不同的host配置
        context ?:return ToastUtils.showShort("context is null")
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.str_server_host_dokit))
            .setSingleChoiceItems(names,pos){ dialog, which ->
                setBaseHost(hosts[which])
                dialog.dismiss()
            }
            .show()
    }
}