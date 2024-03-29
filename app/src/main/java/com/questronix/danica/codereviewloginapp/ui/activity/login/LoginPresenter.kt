package com.questronix.danica.codereviewloginapp.ui.activity.login


import android.text.TextUtils
import com.google.gson.JsonParser
import com.questronix.danica.codereviewloginapp.base.BasePresenter
import com.questronix.danica.codereviewloginapp.model.Users
import com.questronix.danica.codereviewloginapp.network.ApiServices
import io.reactivex.Flowable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class LoginPresenter(loginContract: LoginView) : BasePresenter<LoginView>(loginContract) {
    private var subscription: Disposable? = null
    private var message: String = ""
    @Inject
    lateinit var apiServices: ApiServices

     fun signIn(username: String) {
        if (TextUtils.isEmpty(username)){
            view.emptyCredentials()
            return
        }
        view.showloading()

        subscription = apiServices
            .getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideloading() }
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