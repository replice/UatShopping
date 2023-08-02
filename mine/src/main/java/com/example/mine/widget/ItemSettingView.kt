package com.example.mine.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.TypedArrayUtils.getResourceId
import androidx.databinding.ObservableField
import com.example.mine.R
import com.example.mine.databinding.VItemSettingsBinding


class ItemSettingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr){

    private var itemBean = ItemSettingsBean()
    private val obItemInfo = ObservableField<ItemSettingsBean>(itemBean)



    init {
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true).apply {
            info = obItemInfo
        }
        setBackgroundColor(Color.WHITE)

        // region  2、读取配置属性，才能对外使用Databinding
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSettingsView).apply {
            //icon设置
            itemBean.iconRes =
                getResourceId(R.styleable.ItemSettingsView_icon, com.example.service.R.drawable.icon_server_host)
            val iconRGB = getColor(R.styleable.ItemSettingsView_iconColor, 0)
            itemBean.iconColor = iconRGB
            //title设置
            itemBean.title = getString(R.styleable.ItemSettingsView_title) ?: ""
            val titleRGB = getColor(
                R.styleable.ItemSettingsView_titleColor,
                resources.getColor(com.example.common.R.color.colorPrimaryText)
            )
            itemBean.titleColor = titleRGB
            //desc设置
            itemBean.desc = getString(R.styleable.ItemSettingsView_desc) ?: ""
            val descRGB = getColor(R.styleable.ItemSettingsView_descColor, 0)
            itemBean.descColor = descRGB
            //arrow设置
            itemBean.arrowRes =
                getResourceId(R.styleable.ItemSettingsView_arrow, com.example.service.R.drawable.ic_right)
            val arrowRGB = getColor(
                R.styleable.ItemSettingsView_arrowColor,
                resources.getColor(com.example.service.R.color.colorSecondaryText)
            )
            itemBean.arrowColor = arrowRGB
        }
        // 回收 recycle
        attributes.recycle()
        // endregion
    }

    //region 设置资源

    /**
     * 设置整个item的对象info
     */
    fun setInfo(info: ItemSettingsBean) {
        itemBean = info
        obItemInfo.set(info)
    }

    /**
     * 设置title
     */
    fun setTitle(title: String) {
        itemBean.title = title
        // obItemInfo.notifyChange()
    }

    /**
     * 设置内容描述
     */
    fun setDesc(desc: String) {
        itemBean.desc = desc
        // obItemInfo.notifyChange()
    }

    /**
     * 设置icon图标
     */
    fun setIcon(iconRes: Any) {
        itemBean.iconRes = iconRes
        // obItemInfo.notifyChange()
    }

    /**
     * 设置icon图标
     */
    fun setArrow(arrowRes: Any) {
        itemBean.arrowRes = arrowRes
        // obItemInfo.notifyChange()
    }
    //endregion

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return hasOnClickListeners()
    }

}
//3、定义绑定的实体类
@Keep
data class ItemSettingsBean(
    var iconRes: Any = R.drawable.ic_gift_card, //左边图标 有可能是url string, uri
    var title: String = "Title标题", //title标题
    var desc: String = "这是副标题", //副标题，描述
    var titleColor: Int = com.example.common.R.color.colorPrimaryText, //title字的颜色
    var descColor: Int = com.example.common.R.color.colorSecondaryText, //副标题字的颜色
    var iconColor: Int = 0, //icon的颜色
    var arrowColor: Int = 0, //右边箭头图片的颜色
    var arrowRes: Any = com.example.service.R.drawable.ic_right //右边箭头图片
) {
    //itemview的子view点击事件
    var iconListener: View.OnClickListener? = null
    var titleListener: View.OnClickListener? = null
    var descListener: View.OnClickListener? = null
    var arrowListener: View.OnClickListener? = null
}
