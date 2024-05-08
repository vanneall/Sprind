package ru.point.domain.factory.di

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.interfaces.RegisterUserFactory
import ru.point.domain.factory.implementation.RegisterUserFactoryImpl

@Module
class FactoryModule {
    @Provides
    fun provideUserFactory(): RegisterUserFactory {
        return RegisterUserFactoryImpl()
    }
}