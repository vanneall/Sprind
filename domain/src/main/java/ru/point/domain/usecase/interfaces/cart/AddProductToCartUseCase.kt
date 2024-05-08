package ru.point.domain.usecase.interfaces.cart

interface AddProductToCartUseCase {
    fun handle(id: Long)
}