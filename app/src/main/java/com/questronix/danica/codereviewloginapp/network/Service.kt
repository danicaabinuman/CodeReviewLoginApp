package com.questronix.danica.codereviewloginapp.network

import com.questronix.danica.codereviewloginapp.model.CityListResponse
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers


class Service(networkService: NetworkService) {
    private val networkService: NetworkService
    fun getCityList(callback: GetCityListCallback): Subscription {
        return networkService.getCityList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(object : Func1<Throwable?, Observable<out CityListResponse?>?> {
                override fun call(throwable: Throwable?): Observable<out CityListResponse?> {
                    return Observable.error(throwable)
                }
            })
            .subscribe(object : Subscriber<CityListResponse?>() {
                override fun onCompleted() {}
                override fun onError(e: Throwable?) {
                    callback.onError(NetworkError(e))
                }

                override fun onNext(cityListResponse: CityListResponse?) {
                    callback.onSuccess(cityListResponse)
                }
            })
    }

    interface GetCityListCallback {
        fun onSuccess(cityListResponse: CityListResponse?)
        fun onError(networkError: NetworkError?)
    }

    init {
        this.networkService = networkService
    }
}