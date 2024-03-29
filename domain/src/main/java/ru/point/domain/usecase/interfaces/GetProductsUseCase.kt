package ru.point.domain.usecase.interfaces

import ru.point.domain.entity.Product

interface GetProductsUseCase {
    fun handle(): List<Product>
}