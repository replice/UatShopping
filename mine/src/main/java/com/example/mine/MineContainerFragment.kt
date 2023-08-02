package com.example.mine

import android.os.Bundle
import android.view.View
import com.example.common.base.BaseFragment
import com.example.mine.databinding.FragmentContainerMineBinding

/*
* Mine模块的主Fragment，用于内部navigation的容器
* */
class MineContainerFragment: BaseFragment(){

    override fun getLayoutRes() = R.layout.fragment_container_mine

    override fun bindView(view: View, savedInstanceState: Bundle?) = FragmentContainerMineBinding.bind(view)

}