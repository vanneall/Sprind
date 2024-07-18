package ru.point.domain.usecase.implementation.favorite

import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase

class ChangeFavoriteStateUseCaseImpl(
    private val repository: FavoriteRepository
) : ChangeFavoriteStateUseCase {
    override fun handle(id: Long, isFavorite: Boolean) = run {
        if (isFavorite)
            repository.addFavorite(id = id)
        else
            repository.deleteFavorite(id = id)
    }
}