package ru.point.domain.usecase.interfaces.product

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface GetProductsUseCase {
    fun handle(): Observable<List<ProductFeedDto>>
}