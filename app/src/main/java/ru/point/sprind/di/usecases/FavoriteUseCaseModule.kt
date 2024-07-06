package ru.point.sprind.di.usecases

import dagger.Module
import dagger.Provides
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.implementation.favorite.ChangeFavoriteStateUseCaseImpl
import ru.point.domain.usecase.implementation.favorite.GetFavoritesUseCaseImpl
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase

@Module
class FavoriteUseCaseModule {

    @Provides
    fun providerGetFavoriteUseCase(repository: FavoriteRepository): GetFavoritesUseCase {
        return GetFavoritesUseCaseImpl(repository = repository)
    }

    @Provides
    fun provideFavoriteChangeStateUseCase(repository: FavoriteRepository): ChangeFavoriteStateUseCase {
        return ChangeFavoriteStateUseCaseImpl(repository = repository)
    }
}