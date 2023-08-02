package com.example.mine

import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.example.common.base.BaseActivity
import com.example.mine.R
import com.example.mine.databinding.ActivityMineBinding
import com.example.mine.widget.ItemSettingsBean

class MineActivity: BaseActivity<ActivityMineBinding>(){

    override fun getLayoutRes(): Int = R.layout.activity_mine

    override fun initView() {
        super.initView()
        mBinding.apply {
            val bean = ItemSettingsBean()
            isvCard.setInfo(bean)
            bean.title = "你的学习卡"
            isvCard.setIcon(R.drawable.wallpaper_engine)

//            isvCard.setIcon("https://easyicon.net/api/resizeApi.php?id=1283371&size=96")
            isvCard.setOnClickListener {
                Toast.makeText(this@MineActivity,"点击了整个Item",Toast.LENGTH_SHORT).show()
            }
        }
    }
}