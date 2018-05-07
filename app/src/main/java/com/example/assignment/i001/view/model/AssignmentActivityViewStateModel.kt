package com.example.assignment.i001.view.model

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.RecyclerView
import com.example.assignment.helpers.ViewPagerModified

class AssignmentActivityViewStateModel {
    companion object {
        fun restoreViewModelState(savedInstanceState: Bundle?, viewModel: AssignmentActivityViewModel, stateKey: String): AssignmentActivityViewModel {
            val localViewModel = savedInstanceState?.getSerializable(stateKey)
            return if (null != localViewModel) {
                localViewModel as AssignmentActivityViewModel
            } else {
                viewModel
            }
        }

        fun restoreViewPagerState(viewPager: ViewPagerModified, viewModel: AssignmentActivityViewModel) {
            if (null != viewModel.mViewPagerState) {
                viewPager.onRestoreInstanceState(viewModel.mViewPagerState)
            }
        }

        fun restoreRecyclerViewState(recyclerView: RecyclerView, viewModel: AssignmentActivityViewModel, handler: Handler) {
            if (null != viewModel.mRecyclerViewState) {
                run {
                    recyclerView.layoutManager.onRestoreInstanceState(viewModel.mRecyclerViewState)
                }
            }
        }

        fun saveViewPagerState(viewPager: ViewPagerModified, viewModel: AssignmentActivityViewModel): AssignmentActivityViewModel {
            viewModel.mViewPagerState = viewPager.onSaveInstanceState()
            return viewModel
        }

        fun saveRecyclerViewState(recyclerView: RecyclerView, viewModel: AssignmentActivityViewModel): AssignmentActivityViewModel {
            viewModel.mRecyclerViewState = recyclerView.layoutManager.onSaveInstanceState();
            return viewModel
        }
    }
}