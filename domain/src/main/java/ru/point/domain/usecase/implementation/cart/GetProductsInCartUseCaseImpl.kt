package ru.point.domain.usecase.implementation.cart

import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase

class GetProductsInCartUseCaseImpl(
    private val repository: CartRepository
) : GetProductsInCartUseCase {
    override fun handle() = repository.getProducts()
}