package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.dto.product.ProductInfoDto

interface ProductRepository {
    fun getMainPageInfo(): Single<ComplexProductDto>
    fun getProductsPaging(): Observable<PagingData<FeedProductResponse>>
    fun getProductsByName(name: String): Observable<PagingData<FeedProductResponse>>
    fun getProductById(id: Long): Single<ProductInfoDto>
}