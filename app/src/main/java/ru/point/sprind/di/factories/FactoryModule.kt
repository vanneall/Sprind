package ru.point.sprind.di.factories

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.implementation.NewUserFactoryImpl
import ru.point.domain.factory.interfaces.NewUserFactory

@Module
class FactoryModule {
    @Provides
    fun provideUserFactory(): NewUserFactory {
        return NewUserFactoryImpl()
    }
}