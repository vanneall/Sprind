package ru.point.sprind

import android.app.Application
import ru.point.storage.SettingsManager
import javax.inject.Inject

class SprindApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)

        component.inject(this)
    }

    @Inject
    lateinit var initSettingsManager: SettingsManager
}