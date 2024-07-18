package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.cart.CartPageInfoResponse
import ru.point.domain.entity.view.ViewObject

interface CartRepository {
    fun getPageInfo(): Single<CartPageInfoResponse>

    fun getProducts(): Observable<PagingData<ViewObject>>

    fun addProduct(id: Long): Completable

    fun makeOrder(): Completable

    fun deleteFromCart(id: Long): Completable
}