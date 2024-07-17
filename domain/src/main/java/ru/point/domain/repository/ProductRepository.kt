package ru.point.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.category.CategoryItemResponse
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.dto.product.ProductInfoDto

interface ProductRepository {
    fun getMainPageInfo(): Single<ComplexProductDto>
    fun getProductsPaging(
        offset: Int,
        limit: Int,
        request: String?
    ): Single<List<FeedProductResponse>>
    fun getProductsByName(name: String): Single<List<FeedProductResponse>>
    fun getProductById(id: Long): Single<ProductInfoDto>
    fun getAvailableCategories(): Single<List<CategoryItemResponse>>
}