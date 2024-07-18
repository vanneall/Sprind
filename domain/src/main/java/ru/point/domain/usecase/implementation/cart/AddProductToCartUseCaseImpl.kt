package ru.point.domain.usecase.implementation.cart

import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase

class AddProductToCartUseCaseImpl(
    private val repository: CartRepository
) : AddProductToCartUseCase {
    override fun handle(id: Long) = repository.addProduct(id = id)
}