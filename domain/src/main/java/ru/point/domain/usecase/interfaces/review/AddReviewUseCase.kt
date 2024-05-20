package ru.point.domain.usecase.interfaces.review

import io.reactivex.rxjava3.core.Completable

interface AddReviewUseCase {
    fun handle(
        productId: Long,
        rating: Float,
        description: String,
        advantage: String?,
        disadvantage: String?
    ): Completable
}