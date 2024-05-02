package ru.point.domain.usecase.implementation

import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.AddProductToCartUseCase
import javax.inject.Inject

class AddProductToCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : AddProductToCartUseCase {
    override fun handle(id: Long) {
        repository.addProduct(id = id)
    }
}