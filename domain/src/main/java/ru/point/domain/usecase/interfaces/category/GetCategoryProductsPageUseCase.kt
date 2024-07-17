package ru.point.domain.usecase.interfaces.category

import androidx.paging.rxjava3.RxPagingSource
import ru.point.domain.entity.view.ViewObject

interface GetCategoryProductsPageUseCase {
    fun handle(categoryId: Long): RxPagingSource<Int, ViewObject>
}