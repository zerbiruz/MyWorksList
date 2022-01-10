package com.example.myworkslist

import android.app.Application
import timber.log.Timber

class ProjectItemApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}