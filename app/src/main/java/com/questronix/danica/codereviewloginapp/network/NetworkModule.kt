package com.questronix.danica.codereviewloginapp.network

import com.questronix.danica.codereviewloginapp.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.lang.String
import javax.inject.Singleton


@Module
class NetworkModule(cacheFile: File) {
    var cacheFile: File
    @Provides
    @Singleton
    fun provideCall(): Retrofit {
        var cache: Cache? = null
        try {
            cache = Cache(cacheFile, 10 * 1024 * 1024)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original: Request = chain.request()

                // Customize the request
                val request: Request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .removeHeader("Pragma")
                    .header(
                        "Cache-Control",
                        String.format("max-age=%d", /*BuildConfig.CACHETIME*/)
                    )
                    .build()
                val response = chain.proceed(request)
                response.cacheResponse()
                // Customize or return the response
                response
            }
            .cache(cache)
            .build()
        return Retrofit.Builder()
            //.baseUrl(BuildConfig.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(
        retrofit: Retrofit
    ): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesService(
        networkService: NetworkService?
    ): Service {
        return Service(networkService!!)
    }

    init {
        this.cacheFile = cacheFile
    }
}