package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase
import javax.inject.Inject

class GetProductsByNameUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
) : GetProductsByNameUseCase {
    override fun handle(search: String): Observable<List<ProductFeedDto>> {
        return repository.getProductsByName(name = search)
    }
}