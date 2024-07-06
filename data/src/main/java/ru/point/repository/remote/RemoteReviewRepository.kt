package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.review.CreatedReviewDto
import ru.point.domain.entity.dto.review.ReviewDto
import ru.point.domain.repository.ReviewRepository
import ru.point.retrofit.api.ReviewApi

class RemoteReviewRepository(
    private val api: ReviewApi,
) : ReviewRepository {

    override fun getByProductId(id: Long): Observable<List<ReviewDto>> {
        return api.getReviewsById(id = id).subscribeOn(Schedulers.io())
    }

    override fun addReview(productId: Long, dto: CreatedReviewDto): Completable {
        return api.postReview(id = productId, dto = dto).subscribeOn(Schedulers.io())
    }
}