package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.cart.CartPageInfoResponse
import ru.point.domain.entity.response.product.FeedProductResponse

interface CartRepository {
    fun getPageInfo(): Single<CartPageInfoResponse>

    fun getProducts(offset: Int, limit: Int): Single<List<FeedProductResponse>>

    fun addProduct(id: Long): Completable

    fun makeOrder(): Completable

    fun deleteFromCart(id: Long): Completable
}