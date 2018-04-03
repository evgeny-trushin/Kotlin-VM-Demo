package com.example.assignment.i001.View.model


import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager

import java.io.Serializable
import android.os.Parcelable
import com.example.assignment.i001.View.activity.AssignmentActivity
import com.example.assignment.i001.View.adapter.SimpleRecyclerViewAdapter
import com.example.assignment.i001.View.adapter.SimpleViewPager


class AssignmentActivityViewModel(
        var text: String,
        private val list: MutableList<String>,
        @Transient var manager: FragmentManager,
        var color: Int,
        private val fragmentNumbers: Int
) : Serializable {
    var mViewPagerState: Parcelable? = null
    fun getRecyclerAdapter(context: Context) = SimpleRecyclerViewAdapter(context, list)
    fun getViewPagerAdapter() = SimpleViewPager(manager, fragmentNumbers, Bundle())
    fun getBackgroundColor(context: Context) = context.resources.getColor(color)
    fun clickOnButton(context: Context, color: Int) {
        val viewModel = (context as AssignmentActivity).viewModel
        viewModel.color = color
        context.bindViewModel(viewModel)
    }
}

