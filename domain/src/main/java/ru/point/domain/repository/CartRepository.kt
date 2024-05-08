package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface CartRepository {

    fun getProducts(): Observable<List<ProductFeedDto>>

    fun addProduct(id: Long)

}