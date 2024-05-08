package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.review.CreatedReviewDto
import ru.point.domain.entity.dto.review.ReviewDto

interface ReviewRepository {

    fun getByProductId(id: Long): Observable<List<ReviewDto>>

    fun addReview(productId: Long, dto: CreatedReviewDto): Completable
}