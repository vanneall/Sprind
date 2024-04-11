package ru.point.domain.usecase.di.implementation

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.Product
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsUseCase {
    override fun handle(): Observable<List<Product>> =
        repository.getProducts().subscribeOn(Schedulers.io())
}