package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.request.review.CreateReviewRequest
import ru.point.domain.entity.response.review.ReviewResponse
import ru.point.domain.repository.ReviewRepository
import ru.point.retrofit.api.ReviewApi

class RemoteReviewRepository(
    private val api: ReviewApi,
) : ReviewRepository {

    override fun getByProductId(id: Long): Observable<List<ReviewResponse>> {
        return api.getReviewsById(id = id).subscribeOn(Schedulers.io())
    }

    override fun addReview(productId: Long, request: CreateReviewRequest): Completable {
        return api.postReview(id = productId, dto = request).subscribeOn(Schedulers.io())
    }
}