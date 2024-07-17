package ru.point.repository.remote

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.category.CategoryResponse
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.repository.CategoryRepository
import ru.point.retrofit.api.CategoryApi

class CategoryRepositoryImpl(
    private val api: CategoryApi
) : CategoryRepository {
    override fun getProductsByCategoryId(
        offset: Int,
        limit: Int,
        categoryId: Long
    ): Single<List<FeedProductResponse>> {
        return api.getCategoryProductsById(
            offset = offset,
            limit = limit,
            id = categoryId
        ).subscribeOn(Schedulers.io())
    }

    override fun getCategoryInfo(categoryId: Long): Single<CategoryResponse> {
        return api.getCategoryInfoById(id = categoryId).subscribeOn(Schedulers.io())
    }
}