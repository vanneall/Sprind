package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Completable

interface AddReviewUseCase {
    fun handle(productId: Long, rating: Float, description: String): Completable
}