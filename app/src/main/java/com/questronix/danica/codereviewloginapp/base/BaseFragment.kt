package com.questronix.danica.codereviewloginapp.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter<BaseView>> : BaseView, Fragment() {
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context {
        return requireActivity()
    }

}