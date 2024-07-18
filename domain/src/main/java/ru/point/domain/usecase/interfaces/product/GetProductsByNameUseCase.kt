package ru.point.domain.usecase.interfaces.product

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetProductsByNameUseCase {
    fun handle(request: String): Observable<PagingData<ViewObject>>
}