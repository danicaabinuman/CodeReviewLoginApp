package com.questronix.danica.codereviewloginapp.di.components

import com.questronix.danica.codereviewloginapp.base.BaseView
import com.questronix.danica.codereviewloginapp.di.modules.ContextModule
import com.questronix.danica.codereviewloginapp.di.modules.NetworkModule
import com.questronix.danica.codereviewloginapp.ui.activity.login.LoginPresenter
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.accounts.AccountsPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector{
    fun inject(loginPresenter: LoginPresenter)
    fun inject(accountsPresenter: AccountsPresenter)

    @Component.Builder
    interface Builder{
        fun build(): PresenterInjector
        fun networkModule(networkModule: NetworkModule) : Builder
        fun contextModule(contextModule: ContextModule) : Builder

        @BindsInstance
        fun baseView(baseView: BaseView) : Builder

    }
}