package ru.point.domain.factory.implementation

import ru.point.domain.entity.request.review.CreateReviewRequest
import ru.point.domain.factory.interfaces.ReviewRequestFactory

class ReviewRequestFactoryImpl : ReviewRequestFactory {
    override fun create(
        rating: Float,
        description: String,
        advantage: String?,
        disadvantage: String?
    ) = CreateReviewRequest(
        rating = rating,
        description = description,
        advantage = advantage,
        disadvantage = disadvantage
    )
}