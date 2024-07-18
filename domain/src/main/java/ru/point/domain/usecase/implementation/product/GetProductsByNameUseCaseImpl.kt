package ru.point.domain.usecase.implementation.product

import ru.point.domain.paging.NamedProductsPagingSource
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase

class GetProductsByNameUseCaseImpl(
    private val pagingSourceFactory: NamedProductsPagingSource.Factory
) : GetProductsByNameUseCase {
    override fun handle(request: String) = pagingSourceFactory.create(request = request)
}