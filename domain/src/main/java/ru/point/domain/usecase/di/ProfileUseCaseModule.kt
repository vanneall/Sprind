package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.interfaces.RegisterUserFactory
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.implementation.auth.AuthorizeUseCaseImpl
import ru.point.domain.usecase.implementation.auth.RegisterUserUseCaseImpl
import ru.point.domain.usecase.implementation.profile.GetUserInfoUseCaseImpl
import ru.point.domain.usecase.interfaces.auth.AuthorizeUseCase
import ru.point.domain.usecase.interfaces.auth.RegisterUserUseCase
import ru.point.domain.usecase.interfaces.profile.GetUserInfoUseCase

@Module
class ProfileUseCaseModule {

    @Provides
    fun provideAuthorizeUseCase(repository: UserRepository): AuthorizeUseCase {
        return AuthorizeUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideRegistrationUseCase(
        factory: RegisterUserFactory,
        repository: UserRepository,
    ): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(factory = factory, repository = repository)
    }

    @Provides
    fun provideGetUserInfoUseCase(repository: UserRepository): GetUserInfoUseCase {
        return GetUserInfoUseCaseImpl(repository = repository)
    }
}