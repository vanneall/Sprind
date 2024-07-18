package ru.point.domain.usecase.interfaces.shop

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject

interface GetShopProductsUseCase {
    fun handle(shopId: Long): RxPagingSource<Int, ViewObject>
}