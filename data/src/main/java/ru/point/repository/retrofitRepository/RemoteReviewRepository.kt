package ru.point.repository.retrofitRepository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.CreatedReviewDto
import ru.point.domain.entity.dto.ReviewDto
import ru.point.domain.repository.ReviewRepository
import ru.point.repository.retrofit.ReviewApi
import javax.inject.Inject

class RemoteReviewRepository @Inject constructor(
    private val api: ReviewApi,
) : ReviewRepository {

    override fun getByProductId(id: Long): Observable<List<ReviewDto>> {
        return api.getReviewsById(id = id).subscribeOn(Schedulers.io())
    }

    override fun addReview(productId: Long, dto: CreatedReviewDto): Completable {
        return api.postReview(id = productId, dto = dto).subscribeOn(Schedulers.io())
    }
}