package ru.point.domain.usecase.interfaces.product

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.mapper.interfaces.ProductDtoToListViewMapper

interface GetProductByIdUseCase {
    fun invoke(
        id: Long,
        productDtoToListViewMapper: ProductDtoToListViewMapper,
    ): Single<List<ViewObject>>
}