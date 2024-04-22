package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.FeedProductDto
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsUseCase {
    override fun handle(): Observable<List<FeedProductDto>> =
        repository.getProducts().subscribeOn(Schedulers.io())
}