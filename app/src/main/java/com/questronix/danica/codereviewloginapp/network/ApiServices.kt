package com.questronix.danica.codereviewloginapp.network

import com.questronix.danica.codereviewloginapp.model.Users
import io.reactivex.Observable
import retrofit2.http.GET

interface   ApiServices {
    @GET("users")
    fun getUsers(): Observable<List<Users>>
}