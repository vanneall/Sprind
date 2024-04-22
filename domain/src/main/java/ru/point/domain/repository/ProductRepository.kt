package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.FeedProductDto
import ru.point.domain.entity.ProductDto

interface ProductRepository {
    fun getProducts(): Observable<List<FeedProductDto>>
    fun getProductsByName(name: String): Observable<List<FeedProductDto>>
    fun getProductById(id: Long): Single<ProductDto>
}