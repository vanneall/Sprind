package ru.point.domain.usecase.implementation.cart

import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.MakeOrderUseCase

class MakeOrderUseCaseImpl(
    private val repository: CartRepository
) : MakeOrderUseCase {
    override fun handle() = repository.makeOrder()
}