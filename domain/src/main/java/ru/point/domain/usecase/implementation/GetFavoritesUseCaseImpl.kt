package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.GetFavoritesUseCase
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository,
) : GetFavoritesUseCase {
    override fun handle(): Observable<List<FeedProductDto>> {
        return repository.getFavorite()
    }
}