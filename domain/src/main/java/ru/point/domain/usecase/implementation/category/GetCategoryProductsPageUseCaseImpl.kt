package ru.point.domain.usecase.implementation.category

import ru.point.domain.repository.CategoryRepository
import ru.point.domain.usecase.interfaces.category.GetCategoryProductsPageUseCase

class GetCategoryProductsPageUseCaseImpl(
    private val repository: CategoryRepository
) : GetCategoryProductsPageUseCase {
    override fun handle(categoryId: Long) = repository.getProductsByCategoryId(categoryId)
}