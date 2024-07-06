package ru.point.sprind.di.factories

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.implementation.RegisterUserFactoryImpl
import ru.point.domain.factory.interfaces.RegisterUserFactory

@Module
class FactoryModule {
    @Provides
    fun provideUserFactory(): RegisterUserFactory {
        return RegisterUserFactoryImpl()
    }
}