package ru.point.domain.usecase.interfaces.product

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.product.card.ProductFeedVo

interface GetProductsByNameUseCase {
    fun handle(search: String): Observable<PagingData<ProductFeedVo>>
}