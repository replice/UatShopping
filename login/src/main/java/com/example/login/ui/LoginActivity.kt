package com.example.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.common.base.BaseActivity
import com.example.common.ktx.context
import com.example.login.LoginViewModel
import com.example.login.R
import com.example.login.databinding.ActivityLoginBinding
import com.example.login.net.RegisterRsp
import com.example.service.repo.UserDbHelper

@Route(path = "/login/login")
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_login

    private val viewModel: LoginViewModel by viewModel()

    override fun initView() {
        super.initView()
        mBinding.apply {
            vm = viewModel
            mtoolbarLogin.setNavigationOnClickListener {
                finish()
            }
            tvRegisterLogin.setOnClickListener {
                ToastUtils.showShort("当前课程项目未实现注册账号功能")
            }
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.apply {
            liveRegisterRsp.observerKt {
                if(it?.is_register == RegisterRsp.FLAG_IS_REGISTERED){
                   login()
                }
            }
            liveLoginRsp.observerKt {
                it.also {
                    it?.let {
                        UserDbHelper.insertUserInfo(context,it)
                    }
                    //关闭Activity
                    finish()
                }
            }
        }
    }

}