package ru.point.domain.usecase.implementation.favorite

import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase

class GetFavoritesUseCaseImpl(
    private val repository: FavoriteRepository
) : GetFavoritesUseCase {
    override fun handle() = repository.getFavorite()
}