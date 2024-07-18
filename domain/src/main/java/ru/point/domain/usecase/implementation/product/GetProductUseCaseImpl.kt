package ru.point.domain.usecase.implementation.product

import ru.point.domain.paging.FeedPagingSource
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

class GetProductUseCaseImpl(
    private val pagingSource: FeedPagingSource
) : GetProductsUseCase {
    override fun handle() = pagingSource
}