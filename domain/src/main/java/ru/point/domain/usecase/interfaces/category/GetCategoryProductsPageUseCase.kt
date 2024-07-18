package ru.point.domain.usecase.interfaces.category

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetCategoryProductsPageUseCase {
    fun handle(categoryId: Long): Observable<PagingData<ViewObject>>
}