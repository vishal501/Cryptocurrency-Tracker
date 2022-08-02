package com.trainee.appinventiv.cryptocurrency


import android.app.Application
import androidx.lifecycle.LifecycleObserver

class BaseApplication: Application(), LifecycleObserver {

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onLowMemory() {
        super.onLowMemory()
        System.gc()
    }
}