package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.request.review.CreateReviewRequest
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.ReviewRepository
import ru.point.repository.paging.ProductReviewPagingSource
import ru.point.retrofit.api.ReviewApi

class RemoteReviewRepository(
    private val api: ReviewApi,
) : ReviewRepository {

    override fun getByProductId(id: Long): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { ProductReviewPagingSource(productId = id, api = api) }
        ).observable
            .subscribeOn(Schedulers.io())
    }

    override fun addReview(productId: Long, request: CreateReviewRequest): Completable {
        return api.createReview(id = productId, requestDto = request)
            .subscribeOn(Schedulers.io())
    }
}