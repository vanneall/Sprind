package ru.point.domain.usecase.implementation.review

import ru.point.domain.factory.interfaces.ReviewRequestFactory
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.interfaces.review.AddReviewUseCase

class AddReviewUseCaseImpl(
    private val repository: ReviewRepository,
    private val factory: ReviewRequestFactory
) : AddReviewUseCase {

    override fun handle(
        productId: Long,
        rating: Float,
        description: String,
        advantage: String?,
        disadvantage: String?
    ) = repository.addReview(
        productId = productId,
        request = factory.create(
            rating = rating,
            description = description,
            advantage = advantage,
            disadvantage = disadvantage
        )
    )
}