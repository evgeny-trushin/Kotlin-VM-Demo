package com.example.assignment.i001.view.model


import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager

import java.io.Serializable
import android.os.Parcelable
import com.example.assignment.i001.R
import com.example.assignment.i001.view.activity.AssignmentActivity
import com.example.assignment.i001.view.adapter.SimpleRecyclerViewAdapter
import com.example.assignment.i001.view.adapter.SimpleViewPager


data class AssignmentActivityViewModel(
        val list: List<String>,
        var text: String = "",
        @Transient var manager: FragmentManager? = null,
        var color: Int,
        private val fragmentNumbers: Int
) : Serializable {
    @Transient var mViewPagerState: Parcelable? = null
    fun getBackgroundColor(context: Context) = context.resources.getColor(color)
    fun clickOnButton(context: Context, color: Int) {
        val viewModel = (context as AssignmentActivity).viewModel
        viewModel.color = color
        context.bindViewModel(viewModel, saveState = true)
    }

    var mRecyclerViewState: Parcelable? = null

    private lateinit var recyclerView: SimpleRecyclerViewAdapter
    private var listHash: Int = 0

    fun getRecyclerAdapter(list: List<String>): SimpleRecyclerViewAdapter {
        if (!this::recyclerView.isInitialized
                || listHash != list.hashCode()
        ) {
            recyclerView = SimpleRecyclerViewAdapter(list)
            listHash = list.hashCode()
        }
        return recyclerView
    }

    fun getViewPagerAdapter()= SimpleViewPager(manager!!, fragmentNumbers, Bundle())
}
