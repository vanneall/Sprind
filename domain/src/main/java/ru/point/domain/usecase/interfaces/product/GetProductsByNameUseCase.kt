package ru.point.domain.usecase.interfaces.product

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface GetProductsByNameUseCase {
    fun handle(search: String): Observable<List<ProductFeedDto>>
}