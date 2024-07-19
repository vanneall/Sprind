package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.request.review.CreateReviewRequest
import ru.point.domain.entity.view.ViewObject

interface ReviewRepository {

    fun getByProductId(id: Long): Observable<PagingData<ViewObject>>

    fun addReview(productId: Long, request: CreateReviewRequest): Completable
}