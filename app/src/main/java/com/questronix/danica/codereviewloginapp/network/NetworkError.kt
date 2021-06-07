package com.questronix.danica.codereviewloginapp.network

import android.text.TextUtils
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED


class NetworkError(val error: Throwable?) : Throwable(error) {
    override val message: String
        get() = (error!!.message)!!
    val isAuthFailure: Boolean
        get() = error is HttpException &&
                (error as HttpException?)!!.code() === HTTP_UNAUTHORIZED
    val isResponseNull: Boolean
        get() {
            return error is HttpException && (error as HttpException?)!!.response() == null
        }
    val appErrorMessage: String?
        get() {
            if (error is IOException) return NETWORK_ERROR_MESSAGE
            if (!(error is HttpException)) return DEFAULT_ERROR_MESSAGE
            val response: retrofit2.Response<*>? = (error as HttpException?)!!.response()
            if (response != null) {
                val status: String? = getJsonStringFromResponse(response)
                if (!TextUtils.isEmpty(status)) return status
                val headers: Map<String, List<String>> = response.headers().toMultimap()
                if (headers.containsKey(ERROR_MESSAGE_HEADER)) return headers.get(
                    ERROR_MESSAGE_HEADER
                )!!
                    .get(0)
            }
            return DEFAULT_ERROR_MESSAGE
        }

    protected fun getJsonStringFromResponse(response: retrofit2.Response<*>): String? {
        try {
            val jsonString: String = response.errorBody()!!.string()
            val errorResponse: Response = Gson().fromJson(
                jsonString,
                Response::class.java
            )
            return errorResponse.status
        } catch (e: Exception) {
            return null
        }
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that: NetworkError = o as NetworkError
        return if (error != null) (error == that.error) else that.error == null
    }

    override fun hashCode(): Int {
        return if (error != null) error.hashCode() else 0
    }

    companion object {
        val DEFAULT_ERROR_MESSAGE: String = "Something went wrong! Please try again."
        val NETWORK_ERROR_MESSAGE: String = "No Internet Connection!"
        private val ERROR_MESSAGE_HEADER: String = "Error-Message"
    }
}