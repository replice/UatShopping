package com.example.course

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.common.base.BaseFragment
import com.example.course.databinding.FragmentCourseBinding

class CourseFragment: BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.fragment_course

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view)
    }
}