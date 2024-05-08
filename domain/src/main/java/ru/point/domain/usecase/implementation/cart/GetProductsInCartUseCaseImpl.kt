package ru.point.domain.usecase.implementation.cart

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.mapper.implementations.ProductDtoForCartMapper
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import javax.inject.Inject

class GetProductsInCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : GetProductsInCartUseCase {
    override fun handle(): Observable<List<ViewObject>> {
        return repository.getProducts().map { mapper -> ProductDtoForCartMapper().map(mapper) }
    }
}