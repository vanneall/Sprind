package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.implementation.map.SelectAddressUseCaseImpl
import ru.point.domain.usecase.interfaces.map.SelectAddressUseCase

@Module
class MapUseCaseModule {

    @Provides
    fun provideSelectAddressUeCase(repository: UserRepository): SelectAddressUseCase {
        return SelectAddressUseCaseImpl(repository = repository)
    }
}