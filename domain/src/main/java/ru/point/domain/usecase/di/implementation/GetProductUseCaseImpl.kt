package ru.point.domain.usecase.di.implementation

import ru.point.domain.entity.Product
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository
) : GetProductsUseCase {
    override fun handle(): List<Product> = repository.getProducts()
}