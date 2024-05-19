package ru.point.sprind.components

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.yandex.mapkit.MapKitFactory
import ru.point.manager.SettingsManager
import javax.inject.Inject

class SprindApplication : Application() {

    @Inject
    lateinit var initSettingsManager: SettingsManager

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
        component.inject(this)
        MapKitFactory.setApiKey("f425d115-72d7-4ff9-bff7-20f64fbd8769")
        MapKitFactory.initialize(this)
    }

    fun applyTheme(isDark: Boolean) {
        Log.d("App theme", "Theme applied")
        val mode = if (isDark) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }


    companion object {
        lateinit var component: AppComponent
            private set
    }
}