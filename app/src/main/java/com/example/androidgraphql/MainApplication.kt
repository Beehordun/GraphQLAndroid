package com.example.androidgraphql

import android.app.Application
import com.example.androidgraphql.di.AppComponent
import com.example.androidgraphql.di.DaggerAppComponent

class MainApplication: Application() {

    val appComponent: AppComponent
        get() = staticAppComponent

    override fun onCreate() {
        super.onCreate()

        staticAppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        @JvmStatic lateinit var staticAppComponent: AppComponent
    }
}