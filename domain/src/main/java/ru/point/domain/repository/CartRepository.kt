package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface CartRepository {

    fun getProducts(): Observable<PagingData<ProductFeedDto>>

    fun addProduct(id: Long): Completable

    fun makeOrder(): Completable

    fun deleteFromCart(id: Long): Completable
}