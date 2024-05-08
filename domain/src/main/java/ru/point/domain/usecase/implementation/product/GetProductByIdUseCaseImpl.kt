package ru.point.domain.usecase.implementation.product

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductByIdUseCase
import ru.point.domain.mapper.interfaces.ProductDtoToListViewMapper
import javax.inject.Inject

class GetProductByIdUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
) : GetProductByIdUseCase {
    override fun invoke(
        id: Long,
        productDtoToListViewMapper: ProductDtoToListViewMapper,
    ): Single<List<ViewObject>> {
        return repository.getProductById(id = id).map(productDtoToListViewMapper::map)
    }
}