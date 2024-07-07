package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.complex.CartPageInfoDto
import ru.point.domain.entity.utils.ResponseItem

interface CartRepository {
    fun getPageInfo(): Single<CartPageInfoDto>

    fun getProducts(): Observable<PagingData<ResponseItem>>

    fun addProduct(id: Long): Completable

    fun makeOrder(): Completable

    fun deleteFromCart(id: Long): Completable
}