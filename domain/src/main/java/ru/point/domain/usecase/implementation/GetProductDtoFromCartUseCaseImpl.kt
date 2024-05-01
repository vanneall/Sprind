package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.mapper.ProductDtoForCartMapper
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.GetProductDtoFromCartUseCase
import javax.inject.Inject

class GetProductDtoFromCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : GetProductDtoFromCartUseCase {
    override fun handle(): Observable<List<ViewObject>> {
        return repository.getProducts().map { mapper -> ProductDtoForCartMapper().map(mapper) }
    }
}