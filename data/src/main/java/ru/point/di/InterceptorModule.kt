package ru.point.di

import dagger.Module
import dagger.Provides
import ru.point.retrofit.interceptor.AuthorizationInterceptor
import ru.point.manager.SettingsManager

@Module
class InterceptorModule {

    @Provides
    fun provideAuthInterceptor(manager: SettingsManager): AuthorizationInterceptor {
        return AuthorizationInterceptor(manager = manager)
    }
}