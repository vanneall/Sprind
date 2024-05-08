package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.entity.dto.product.ProductInfoDto

interface ProductRepository {
    fun getProducts(): Observable<List<ProductFeedDto>>
    fun getProductsByName(name: String): Observable<List<ProductFeedDto>>
    fun getProductById(id: Long): Single<ProductInfoDto>
}