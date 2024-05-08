package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ReviewVo

interface GetReviewsByProductIdUseCase {
    fun handle(id: Long): Observable<List<ReviewVo>>
}