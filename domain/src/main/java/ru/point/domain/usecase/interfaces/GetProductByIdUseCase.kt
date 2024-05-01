package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.view.ViewObject

interface GetProductByIdUseCase {
    fun invoke(
        id: Long,
        productDtoToListViewMapper: ProductDtoToListViewMapper,
    ): Single<List<ViewObject>>
}