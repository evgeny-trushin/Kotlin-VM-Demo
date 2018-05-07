package com.example.assignment.i001.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.example.assignment.helpers.ViewPagerModified
import com.example.assignment.i001.R
import com.example.assignment.i001.databinding.ActivityAssignmentBinding
import com.example.assignment.i001.view.model.AssignmentActivityViewModel
import com.example.assignment.i001.view.model.AssignmentActivityViewStateModel
import me.relex.circleindicator.CircleIndicator


class AssignmentActivity : AppCompatActivity() {
    private lateinit var mBind: ActivityAssignmentBinding
    var viewModel = AssignmentActivityViewModel(
            list = listOf("Item 1","Item 2","Item 3","Item 4","Item 5","Item 6"),
            color = R.color.colorWhite,
            fragmentNumbers = 4
    )
    private var mHandler = Handler(Looper.getMainLooper())
    private val mStateKey = "STATE"
    private var firstInit = true
    private lateinit var mViewPager: ViewPagerModified
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_assignment)
        mRecyclerView = mBind.root.findViewById(R.id.recycler_view) as RecyclerView
        mViewPager = mBind.root.findViewById(R.id.pager) as ViewPagerModified
        mViewPager.addOnAdapterChangeListener { pager, _, _ ->
            run {
                AssignmentActivityViewStateModel.restoreViewPagerState(pager as ViewPagerModified, viewModel)
                (mBind.root.findViewById(R.id.indicator) as CircleIndicator).setViewPager(pager)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel = AssignmentActivityViewStateModel.restoreViewModelState(savedInstanceState, viewModel, mStateKey)
    }

    override fun onResume() {
        super.onResume()
        viewModel.manager = supportFragmentManager
        bindViewModel(viewModel,false)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        AssignmentActivityViewStateModel.saveViewPagerState(mViewPager, viewModel)
        AssignmentActivityViewStateModel.saveRecyclerViewState(mRecyclerView, viewModel)
        outState?.putSerializable(mStateKey, viewModel)
    }

    fun bindViewModel(localViewModel: AssignmentActivityViewModel, saveState: Boolean) {
        if (saveState) {
            viewModel = AssignmentActivityViewStateModel.saveRecyclerViewState(mRecyclerView, viewModel)
            viewModel = AssignmentActivityViewStateModel.saveViewPagerState(mViewPager, viewModel)
        }
        bindViewModel(localViewModel)
        AssignmentActivityViewStateModel.restoreRecyclerViewState(mRecyclerView, viewModel, mHandler)
    }

    private fun bindViewModel(localViewModel: AssignmentActivityViewModel) {
        viewModel = localViewModel
        mBind.viewModel = viewModel
        mBind.buttons?.viewModel = viewModel
        mBind.executePendingBindings()
    }
}