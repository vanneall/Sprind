package ru.point.domain.usecase.implementation.category

import ru.point.domain.paging.CategoryPagingSource
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase

class GetCategoryProductsPageUseCaseImpl(
    private val pagingSourceFactory: CategoryPagingSource.Factory
) : GetCategoryProductsPageUseCase {
    override fun handle(categoryId: Long) = pagingSourceFactory.create(categoryId = categoryId)
}