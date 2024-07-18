package ru.point.domain.usecase.interfaces.product

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetProductsUseCase {
    fun handle(): Observable<PagingData<ViewObject>>
}