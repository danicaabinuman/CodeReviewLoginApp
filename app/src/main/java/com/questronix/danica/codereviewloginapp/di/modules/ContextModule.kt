package com.questronix.danica.codereviewloginapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object ContextModule {

    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }
}