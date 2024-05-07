package ru.point.di

import dagger.Module
import dagger.Provides
import ru.point.repository.retrofit.AuthorizationInterceptor
import ru.point.storage.SettingsManager
import javax.inject.Singleton

@Module
class InterceptorModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(manager: SettingsManager): AuthorizationInterceptor {
        return AuthorizationInterceptor(manager = manager)
    }
}