package ru.point.domain.usecase.implementation.review

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.entity.dto.review.CreatedReviewDto
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.interfaces.review.AddReviewUseCase

class AddReviewUseCaseImpl(
    private val repository: ReviewRepository,
) : AddReviewUseCase {

    override fun handle(
        productId: Long,
        rating: Float,
        description: String,
        advantage: String?,
        disadvantage: String?
    ): Completable {
        return repository.addReview(
            productId,
            CreatedReviewDto(rating, description, advantage, disadvantage)
        )
    }
}