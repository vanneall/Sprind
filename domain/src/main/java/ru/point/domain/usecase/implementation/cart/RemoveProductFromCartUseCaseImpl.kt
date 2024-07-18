package ru.point.domain.usecase.implementation.cart

import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.RemoveProductFromCartUseCase

class RemoveProductFromCartUseCaseImpl(
    private val repository: CartRepository
) : RemoveProductFromCartUseCase {
    override fun handle(id: Long) = repository.deleteFromCart(id)
}