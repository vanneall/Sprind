package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.ListView
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.GetProductByIdUseCase
import ru.point.domain.usecase.interfaces.ProductDtoToListViewMapper
import javax.inject.Inject

class GetProductByIdUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
) : GetProductByIdUseCase {
    override fun invoke(
        id: Long,
        productDtoToListViewMapper: ProductDtoToListViewMapper,
    ): Single<List<ListView>> {
        return repository.getProductById(id = id).map(productDtoToListViewMapper::map)
    }
}