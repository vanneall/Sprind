package ru.point.domain.usecase.implementation.product

import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository
) : GetProductsUseCase {
    override fun handle() = repository.getProductsPaging()
}