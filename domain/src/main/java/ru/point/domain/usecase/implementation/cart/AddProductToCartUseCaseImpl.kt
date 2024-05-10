package ru.point.domain.usecase.implementation.cart

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import javax.inject.Inject

class AddProductToCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : AddProductToCartUseCase {
    override fun handle(id: Long): Completable {
        return repository.addProduct(id = id)
    }
}