package com.example.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.room.Database
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.base.BaseFragment
import com.example.mine.MineViewModel
import com.example.mine.R
import com.example.mine.databinding.FragmentMineBinding
import com.example.service.repo.UserDbHelper

class MineFragment: BaseFragment() {

    private val viewModel: MineViewModel by viewModels<MineViewModel>()

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel
            //UI操作
            btnLogoutMine.setOnClickListener {
                UserDbHelper.deleteUserInfo(requireContext())
                ARouter.getInstance().build("/login/login").navigation()
            }

            //跳转userInfoFragment
            ivUserIconMine.setOnClickListener {
                val info = viewModel.liveUserInfo.value
                info?.let {
                    info.company = "自由职业着"
//                    val action = MineFragmentDirections.actionMineFragmentToUserInfoFragment(info)
                }
            }
        }
    }

    override fun initData() {
        super.initData()
        UserDbHelper.getLiveUserInfo(requireContext())?.observeKt {
            viewModel.getUserInfo(it?.token)
        }
    }
}