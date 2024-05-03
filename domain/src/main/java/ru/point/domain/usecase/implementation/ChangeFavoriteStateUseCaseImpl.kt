package ru.point.domain.usecase.implementation

import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.ChangeFavoriteStateUseCase
import javax.inject.Inject

class ChangeFavoriteStateUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : ChangeFavoriteStateUseCase {
    override fun handle(id: Long, isFavorite: Boolean) {
        if (isFavorite) {
            repository.addFavorite(id = id)
        } else {
            repository.deleteFavorite(id = id)
        }
    }
}