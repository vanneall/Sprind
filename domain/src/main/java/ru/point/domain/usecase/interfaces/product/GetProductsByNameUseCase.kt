package ru.point.domain.usecase.interfaces.product

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject

interface GetProductsByNameUseCase {
    fun handle(search: String): RxPagingSource<Int, ViewObject>
}