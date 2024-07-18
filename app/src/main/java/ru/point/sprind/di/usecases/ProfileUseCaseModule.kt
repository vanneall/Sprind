package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.interfaces.AuthUserRequestFactory
import ru.point.domain.factory.interfaces.NewUserFactory
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.implementation.auth.AuthorizeUseCaseImpl
import ru.point.domain.usecase.implementation.auth.RegisterUserUseCaseImpl
import ru.point.domain.usecase.implementation.profile.GetOrdersUseCaseImpl
import ru.point.domain.usecase.implementation.profile.GetUserInfoUseCaseImpl
import ru.point.domain.usecase.interfaces.auth.AuthorizeUseCase
import ru.point.domain.usecase.interfaces.auth.RegisterUserUseCase
import ru.point.domain.usecase.interfaces.profile.GetOrdersUseCase
import ru.point.domain.usecase.interfaces.profile.GetUserInfoUseCase

@Module
class ProfileUseCaseModule {

    @Provides
    fun provideAuthorizeUseCase(
        repository: UserRepository,
        factory: AuthUserRequestFactory
    ): AuthorizeUseCase {
        return AuthorizeUseCaseImpl(repository = repository, factory = factory)
    }

    @Provides
    fun provideRegistrationUseCase(
        factory: NewUserFactory,
        repository: UserRepository,
    ): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(repository = repository, factory = factory)
    }

    @Provides
    fun provideGetUserInfoUseCase(repository: UserRepository): GetUserInfoUseCase {
        return GetUserInfoUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideGetOrdersUseCase(repository: UserRepository): GetOrdersUseCase {
        return GetOrdersUseCaseImpl(repository = repository)
    }
}