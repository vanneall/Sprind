package ru.point.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.point.manager.SettingsManager
import javax.inject.Singleton

@Module
class ManagerModule {

    @Provides
    @Singleton
    fun provideSettingManager(context: Context): SettingsManager {
        return SettingsManager(context = context)
    }

}