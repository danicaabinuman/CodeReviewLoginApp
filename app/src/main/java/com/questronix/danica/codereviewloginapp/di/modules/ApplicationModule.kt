package com.questronix.danica.codereviewloginapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }
}