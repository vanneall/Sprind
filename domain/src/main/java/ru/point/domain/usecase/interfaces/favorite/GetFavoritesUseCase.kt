package ru.point.domain.usecase.interfaces.favorite

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface GetFavoritesUseCase {
    fun handle(): Observable<List<ProductFeedDto>>
}