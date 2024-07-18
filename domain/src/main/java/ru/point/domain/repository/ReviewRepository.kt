package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.request.review.CreateReviewRequest
import ru.point.domain.entity.response.review.ReviewResponse

interface ReviewRepository {

    fun getByProductId(id: Long): Observable<List<ReviewResponse>>

    fun addReview(productId: Long, dto: CreateReviewRequest): Completable
}