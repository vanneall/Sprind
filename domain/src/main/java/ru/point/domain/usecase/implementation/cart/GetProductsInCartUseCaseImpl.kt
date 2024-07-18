package ru.point.domain.usecase.implementation.cart

import ru.point.domain.paging.CartPagingSource
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase

class GetProductsInCartUseCaseImpl(
    private val cartPagingSource: CartPagingSource
) : GetProductsInCartUseCase {
    override fun handle() = cartPagingSource
}