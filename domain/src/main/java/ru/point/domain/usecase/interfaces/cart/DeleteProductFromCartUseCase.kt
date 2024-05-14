package ru.point.domain.usecase.interfaces.cart

import io.reactivex.rxjava3.core.Completable

interface DeleteProductFromCartUseCase {
    fun handle(id: Long): Completable
}