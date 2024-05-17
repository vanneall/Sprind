package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.complex.ComplexProductFeedVoContainer
import ru.point.domain.entity.dto.complex.toComplexProductFeedVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsUseCase {

    override fun handle(): Observable<ComplexProductFeedVoContainer> {
        return repository.getProducts()
            .observeOn(Schedulers.computation())
            .map { dto -> dto.toComplexProductFeedVo() }
    }

}