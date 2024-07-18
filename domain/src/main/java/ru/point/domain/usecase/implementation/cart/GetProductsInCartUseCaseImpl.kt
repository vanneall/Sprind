package ru.point.domain.usecase.implementation.cart

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.paging.CartPagingSource
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase

class GetProductsInCartUseCaseImpl(
    private val cartPagingSource: CartPagingSource
) : GetProductsInCartUseCase {
    override fun handle(): RxPagingSource<Int, ViewObject> = cartPagingSource
}