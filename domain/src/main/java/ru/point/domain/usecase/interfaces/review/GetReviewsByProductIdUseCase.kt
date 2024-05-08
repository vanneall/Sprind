package ru.point.domain.usecase.interfaces.review

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.review.ReviewVo

interface GetReviewsByProductIdUseCase {
    fun handle(id: Long): Observable<List<ReviewVo>>
}