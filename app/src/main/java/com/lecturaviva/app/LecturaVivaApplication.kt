package com.lecturaviva.app

import android.app.Application

class LecturaVivaApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
