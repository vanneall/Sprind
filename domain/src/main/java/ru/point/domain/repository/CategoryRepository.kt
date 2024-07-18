package ru.point.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.category.CategoryResponse
import ru.point.domain.entity.response.product.FeedProductResponse

interface CategoryRepository {
    fun getProductsByCategoryId(
        offset: Int,
        limit: Int,
        categoryId: Long
    ): Single<List<FeedProductResponse>>

    fun getCategoryInfo(categoryId: Long): Single<CategoryResponse>
}