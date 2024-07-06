package ru.point.domain.usecase.implementation.cart

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.DeleteProductFromCartUseCase

class DeleteProductFromCartUseCaseImpl(
    private val repository: CartRepository
) : DeleteProductFromCartUseCase {
    override fun handle(id: Long): Completable {
        return repository.deleteFromCart(id)
    }
}