package com.questronix.danica.codereviewloginapp.base


import com.questronix.danica.codereviewloginapp.di.components.DaggerPresenterInjector
import com.questronix.danica.codereviewloginapp.di.components.PresenterInjector
import com.questronix.danica.codereviewloginapp.di.modules.ContextModule
import com.questronix.danica.codereviewloginapp.di.modules.NetworkModule
import com.questronix.danica.codereviewloginapp.ui.activity.login.LoginPresenter
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.accounts.AccountsPresenter

open class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()


    init {
        inject()
    }

    open fun onViewCreated(){}

    open fun onViewDestroyed(){}

    private fun inject() {
        when (this) {
            is LoginPresenter -> { injector.inject(this)}
            is AccountsPresenter -> {injector.inject(this)}


        }
    }
}