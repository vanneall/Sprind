package ru.point.domain.usecase.implementation.favorite

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository,
) : GetFavoritesUseCase {
    override fun handle(): Observable<List<ProductFeedDto>> {
        return repository.getFavorite()
    }
}