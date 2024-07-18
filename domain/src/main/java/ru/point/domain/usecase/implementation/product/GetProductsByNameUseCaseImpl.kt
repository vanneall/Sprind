package ru.point.domain.usecase.implementation.product

import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase

class GetProductsByNameUseCaseImpl(
    private val repository: ProductRepository
) : GetProductsByNameUseCase {
    override fun handle(request: String) = repository.getProductsByName(request)
}