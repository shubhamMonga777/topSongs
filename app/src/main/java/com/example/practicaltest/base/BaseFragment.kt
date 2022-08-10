package com.example.practicaltest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.practicaltest.util.observe

abstract class BaseFragment<T : ViewDataBinding,VM : BaseViewModel> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int
    private lateinit var viewModel: VM


    private var mViewDataBinding: T? = null
    private var mRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        mRootView = mViewDataBinding?.root
        initView()
        return mRootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRootView = null
        mViewDataBinding?.lifecycleOwner = null
        mViewDataBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        mViewDataBinding!!.lifecycleOwner = this
        mViewDataBinding!!.executePendingBindings()

        with(viewModel) {
            observe(errorMessage) { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    open fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    abstract fun getVM(): VM
    abstract fun initView()
    abstract fun initData()
}