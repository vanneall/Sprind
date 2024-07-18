package ru.point.domain.factory.interfaces

import ru.point.domain.entity.request.review.CreateReviewRequest

interface ReviewRequestFactory {
    fun create(
        rating: Float,
        description: String,
        advantage: String?,
        disadvantage: String?
    ): CreateReviewRequest
}