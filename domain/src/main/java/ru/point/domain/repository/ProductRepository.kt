package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.category.CategoryItemResponse
import ru.point.domain.entity.response.complex.ComplexProductResponse
import ru.point.domain.entity.response.product.ProductInfoResponse
import ru.point.domain.entity.view.ViewObject

interface ProductRepository {
    fun getMainPageInfo(): Single<ComplexProductResponse>
    fun getProductsPaging(): Observable<PagingData<ViewObject>>
    fun getProductsByName(name: String): Observable<PagingData<ViewObject>>
    fun getProductById(id: Long): Single<ProductInfoResponse>
    fun getAvailableCategories(): Single<List<CategoryItemResponse>>
}