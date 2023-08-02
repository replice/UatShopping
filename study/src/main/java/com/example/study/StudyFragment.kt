package com.example.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.common.base.BaseFragment
import com.example.study.databinding.FragmentStudyBinding
import com.example.study.databinding.FragmentStudyBindingImpl

class StudyFragment: BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view)
    }
}