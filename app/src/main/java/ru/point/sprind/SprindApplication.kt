package ru.point.sprind

import android.app.Application

class SprindApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }
}