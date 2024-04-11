package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.Product
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.GetProductsByNameUseCase
import javax.inject.Inject

class GetProductsByNameUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
) : GetProductsByNameUseCase {
    override fun handle(search: String): Observable<List<Product>> {
        return repository.getProductsByName(name = search)
    }
}