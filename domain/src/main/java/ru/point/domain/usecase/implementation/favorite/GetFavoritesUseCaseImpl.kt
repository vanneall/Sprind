package ru.point.domain.usecase.implementation.favorite

import androidx.paging.PagingData
import androidx.paging.map
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.toProductFeedVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.FavoriteRepository
import ru.point.domain.usecase.interfaces.favorite.GetFavoritesUseCase
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository,
) : GetFavoritesUseCase {

    override fun handle(): Observable<PagingData<ViewObject>> {
        return repository.getFavorite()
            .observeOn(Schedulers.computation())
            .map { pagingData ->
                pagingData.map { product -> product.toProductFeedVo() }
            }
    }
}