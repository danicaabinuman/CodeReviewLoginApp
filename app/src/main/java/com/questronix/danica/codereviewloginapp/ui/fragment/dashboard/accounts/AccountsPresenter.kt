package com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.accounts

import com.google.gson.JsonParser
import com.questronix.danica.codereviewloginapp.base.BasePresenter
import com.questronix.danica.codereviewloginapp.network.ApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class AccountsPresenter(accountsView: AccountsView) : BasePresenter<AccountsView>(accountsView) {
    @Inject
    lateinit var apiServices: ApiServices

    private var subscription: Disposable? = null
    private var message: String = ""

    fun getUsers(){
        view.showloading()
        subscription = apiServices
            .getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {view.hideloading() }
            .subscribe(
                { body -> view.onSuccess(body) },
                { error -> if (error is HttpException) {
                    val errorJsonString = error.response()!!.errorBody()?.string()
                    message = JsonParser().parse(errorJsonString)
                        .asJsonObject["Message"]
                        .asString
                    view.onError(message)
                } else {
                    view.onError(error.message.toString())
                }
                }

            )

    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}