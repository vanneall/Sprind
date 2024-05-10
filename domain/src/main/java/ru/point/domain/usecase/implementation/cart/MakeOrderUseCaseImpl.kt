package ru.point.domain.usecase.implementation.cart

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.MakeOrderUseCase
import javax.inject.Inject

class MakeOrderUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : MakeOrderUseCase {
    override fun handle(): Completable {
        return repository.makeOrder()
    }
}