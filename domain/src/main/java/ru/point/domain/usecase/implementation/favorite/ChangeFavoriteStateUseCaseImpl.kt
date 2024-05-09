package ru.point.domain.usecase.implementation.favorite

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import javax.inject.Inject

class ChangeFavoriteStateUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : ChangeFavoriteStateUseCase {
    override fun handle(id: Long, isFavorite: Boolean): Completable {
        return if (isFavorite) {
            repository.addFavorite(id = id)
        } else {
            repository.deleteFavorite(id = id)
        }
    }
}