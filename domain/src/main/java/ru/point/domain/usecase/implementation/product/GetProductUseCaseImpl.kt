package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsUseCase {
    override fun handle(): Observable<List<ProductFeedDto>> =
        repository.getProducts().subscribeOn(Schedulers.io())
}