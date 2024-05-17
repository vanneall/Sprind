package ru.point.domain.usecase.implementation.cart

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.complex.ComplexProductCartVoContainer
import ru.point.domain.entity.dto.complex.toComplexProductCartVoContainer
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import javax.inject.Inject

class GetProductsInCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : GetProductsInCartUseCase {

    override fun handle(): Observable<ComplexProductCartVoContainer> {
        return repository.getProducts().observeOn(Schedulers.computation())
            .map { dto -> dto.toComplexProductCartVoContainer() }
    }
}