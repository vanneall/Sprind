package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.mapper.implementations.FeedProductDtoToProductFeedVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsUseCase {

    private val mapper = FeedProductDtoToProductFeedVo()

    override fun handle(): Observable<List<ViewObject>> =
        repository.getProducts().map { list -> list.map { mapper.map(it) } }
}