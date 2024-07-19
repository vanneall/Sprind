package ru.point.domain.usecase.interfaces.review

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetReviewsByProductIdUseCase {
    fun handle(id: Long): Observable<PagingData<ViewObject>>
}