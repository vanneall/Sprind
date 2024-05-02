package ru.point.domain.usecase.interfaces

interface AddProductToCartUseCase {
    fun handle(id: Long)
}