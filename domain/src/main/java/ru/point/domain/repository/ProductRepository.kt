package ru.point.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.category.CategoryItemResponse
import ru.point.domain.entity.response.complex.ComplexProductResponse
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.entity.response.product.ProductInfoResponse

interface ProductRepository {
    fun getMainPageInfo(): Single<ComplexProductResponse>
    fun getProductsPaging(
        offset: Int,
        limit: Int,
        request: String?
    ): Single<List<FeedProductResponse>>
    fun getProductsByName(name: String): Single<List<FeedProductResponse>>
    fun getProductById(id: Long): Single<ProductInfoResponse>
    fun getAvailableCategories(): Single<List<CategoryItemResponse>>
}