package ru.point.manager

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ru.point.domain.entity.utils.Token
import ru.point.repository.settings.dataStore
import javax.inject.Inject


class SettingsManager @Inject constructor(
    private val context: Context,
) {
    private var settings: Settings? = null

    init {
        read()
    }

    var token: Token
        get() = settings?.token ?: Token("")
        set(value) {
            settings?.let { settings ->
                this.settings = settings.copy(token = value)
                write(this.settings!!)
            }
        }

    var isDarkThemeEnabled: Boolean
        get() = settings?.isDarkThemeEnabled ?: false
        set(value) {
            settings?.let { settings ->
                this.settings = settings.copy(isDarkThemeEnabled = value)
                write(this.settings!!)
            }
        }

    private fun read() {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.data.collect { dataStoreSettings ->
                Log.d("Settings manager", "Settings manager initialized")
                settings = dataStoreSettings

            }
        }
    }

    private fun write(newSettings: Settings) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.updateData { newSettings }
        }
    }
}

@Serializable
data class Settings(
    @Contextual
    val token: Token? = null,
    val isDarkThemeEnabled: Boolean = false
)

