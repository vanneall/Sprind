package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.ProductInfoDto

interface ProductRepository {
    fun getProducts(): Observable<ComplexProductDto>
    fun getProductsByName(name: String): Observable<ComplexProductDto>
    fun getProductById(id: Long): Single<ProductInfoDto>
}