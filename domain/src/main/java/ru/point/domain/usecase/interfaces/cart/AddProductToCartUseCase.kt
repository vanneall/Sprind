package ru.point.domain.usecase.interfaces.cart

import io.reactivex.rxjava3.core.Completable

interface AddProductToCartUseCase {
    fun handle(id: Long): Completable
}