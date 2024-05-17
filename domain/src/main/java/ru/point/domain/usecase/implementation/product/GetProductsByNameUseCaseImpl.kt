package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.complex.ComplexProductFeedVoContainer
import ru.point.domain.entity.dto.complex.toComplexProductFeedVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase
import javax.inject.Inject

class GetProductsByNameUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
) : GetProductsByNameUseCase {

    override fun handle(search: String): Observable<ComplexProductFeedVoContainer> {
        return repository.getProductsByName(name = search)
            .observeOn(Schedulers.computation())
            .map { dto -> dto.toComplexProductFeedVo() }
    }
}