package ru.point.repository.settings

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.point.manager.Settings
import java.io.InputStream
import java.io.OutputStream

internal val Context.dataStore by dataStore("settings.json", SettingsSerializer())

internal class SettingsSerializer : Serializer<Settings> {
    override val defaultValue: Settings
        get() = Settings()

    override suspend fun readFrom(input: InputStream): Settings {
        return try {
            Json.decodeFromString(
                deserializer = Settings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: Settings, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = Settings.serializer(),
                    value = t
                ).toByteArray()
            )
        }
    }
}