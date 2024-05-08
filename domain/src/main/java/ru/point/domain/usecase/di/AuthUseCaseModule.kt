package ru.point.domain.usecase.di

import dagger.Module
import dagger.Provides
import ru.point.domain.factory.RegisterUserFactory
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.implementation.AuthorizeUseCaseImpl
import ru.point.domain.usecase.implementation.RegisterUserUseCaseImpl
import ru.point.domain.usecase.interfaces.AuthorizeUseCase
import ru.point.domain.usecase.interfaces.RegisterUserUseCase

@Module
class AuthUseCaseModule {

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
}