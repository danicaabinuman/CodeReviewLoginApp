package com.questronix.danica.codereviewloginapp.network

import com.questronix.danica.codereviewloginapp.model.CityListResponse
import io.reactivex.Observable
import retrofit2.http.GET



interface NetworkService {
    @GET("v1/city")
    fun getCityList() : Observable<CityListResponse>
}