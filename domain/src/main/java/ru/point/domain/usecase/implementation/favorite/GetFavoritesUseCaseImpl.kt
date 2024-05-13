package ru.point.domain.usecase.implementation.favorite

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.mapper.implementations.FeedProductDtoToProductFeedVo
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository,
) : GetFavoritesUseCase {

    val mapper = FeedProductDtoToProductFeedVo()

    override fun handle(): Observable<List<ViewObject>> {
        return repository.getFavorite().map { list -> list.map { mapper.map(it) }  }
    }
}