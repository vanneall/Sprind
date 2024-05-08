package ru.point.sprind.components

import android.app.Application
import ru.point.manager.SettingsManager
import javax.inject.Inject

class SprindApplication : Application() {

    companion object {
        lateinit var component: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)

        component.inject(this)
    }

    @Inject
    lateinit var initSettingsManager: SettingsManager
}