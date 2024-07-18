package ru.point.sprind.di.factories

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.implementation.AuthUserRequestFactoryImpl
import ru.point.domain.factory.implementation.EmptyAddressResponseFactoryImpl
import ru.point.domain.factory.implementation.NewUserFactoryImpl
import ru.point.domain.factory.implementation.ReviewRequestFactoryImpl
import ru.point.domain.factory.interfaces.AuthUserRequestFactory
import ru.point.domain.factory.interfaces.EmptyAddressResponseFactory
import ru.point.domain.factory.interfaces.NewUserFactory
import ru.point.domain.factory.interfaces.ReviewRequestFactory

@Module
class FactoryModule {
    @Provides
    fun provideUserFactory(): NewUserFactory {
        return NewUserFactoryImpl()
    }

    @Provides
    fun provideEmptyAddressFactory(): EmptyAddressResponseFactory {
        return EmptyAddressResponseFactoryImpl()
    }

    @Provides
    fun provideAuthUserRequestFactory(): AuthUserRequestFactory {
        return AuthUserRequestFactoryImpl()
    }

    @Provides
    fun provideReviewRequestFactory(): ReviewRequestFactory {
        return ReviewRequestFactoryImpl()
    }
}