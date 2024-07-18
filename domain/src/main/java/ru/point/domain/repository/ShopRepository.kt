package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface ShopRepository {
    fun getShopProducts(shopId: Long): Observable<PagingData<ViewObject>>
}