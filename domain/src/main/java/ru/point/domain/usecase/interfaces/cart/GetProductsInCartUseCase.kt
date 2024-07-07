package ru.point.domain.usecase.interfaces.cart

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject

interface GetProductsInCartUseCase {
    fun handle(): RxPagingSource<Int, ViewObject>
}