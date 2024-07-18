package ru.point.domain.usecase.implementation.category

import ru.point.domain.paging.CategoryPagingSource
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase

class GetCategoryProductsPageUseCaseImpl(
    private val categoryPagingSourceFactory: CategoryPagingSource.Factory
) : GetCategoryProductsPageUseCase {
    override fun handle(categoryId: Long) =
        categoryPagingSourceFactory.create(categoryId = categoryId)
}