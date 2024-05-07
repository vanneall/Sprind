package ru.point.storage

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ru.point.domain.entity.utils.Token
import ru.point.repository.preferences.dataStore
import javax.inject.Inject
import javax.inject.Singleton


class SettingsManager @Inject constructor(
    private val context: Context,
) {
    private lateinit var settings: Settings

    init {
        read()
    }

    var token: Token
        get() = settings.token ?: Token("")
        set(value) {
            settings = settings.copy(token = value)
            write(settings)
        }


    private fun read() {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.data.collect { dataStoreSettings ->
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
)

