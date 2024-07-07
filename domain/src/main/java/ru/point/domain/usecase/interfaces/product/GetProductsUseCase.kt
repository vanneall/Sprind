package ru.point.domain.usecase.interfaces.product

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject

interface GetProductsUseCase {
    fun handle(): RxPagingSource<Int, ViewObject>
}