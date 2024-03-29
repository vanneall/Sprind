package ru.point.sprind.di

import dagger.Module
import dagger.Provides
import ru.point.sprind.usecase.GetMordaDelegatesUseCase
import ru.point.sprind.usecase.GetMordaDelegatesUseCaseImpl

@Module
class DelegateModule {

    @Provides
    fun provideMordaDelegates(): GetMordaDelegatesUseCase {
        return GetMordaDelegatesUseCaseImpl()
    }
}