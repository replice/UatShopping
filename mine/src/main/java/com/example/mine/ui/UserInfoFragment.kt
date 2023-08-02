package com.example.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.base.BaseFragment
import com.example.mine.R
import com.example.mine.databinding.FragmentUserInfoBinding

class UserInfoFragment: BaseFragment() {

    //获取传过来的Args数据,所有的参数都在args里 当前类名+Args
    private val args by navArgs<UserInfoFragmentArgs>()

    override fun getLayoutRes(): Int = R.layout.fragment_user_info

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentUserInfoBinding.bind(view).apply {
            //toolbar返回上个界面
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            //保存按钮
            saveBtn.setOnClickListener {
                findNavController().navigateUp()
            }
            info = args.info
        }
    }
}