package com.questronix.danica.codereviewloginapp

import android.app.Application
import com.questronix.danica.codereviewloginapp.di.components.ApplicationComponent
import com.questronix.danica.codereviewloginapp.di.components.DaggerApplicationComponent
import com.questronix.danica.codereviewloginapp.di.modules.ApplicationModule


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Companion.applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    val applicationComponent: ApplicationComponent?
        get() = Companion.applicationComponent

    companion object {
        private var applicationComponent: ApplicationComponent? = null
    }
}
