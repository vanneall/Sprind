package ru.point.domain.usecase.interfaces.shop

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetShopProductsUseCase {
    fun handle(shopId: Long): Observable<PagingData<ViewObject>>
}