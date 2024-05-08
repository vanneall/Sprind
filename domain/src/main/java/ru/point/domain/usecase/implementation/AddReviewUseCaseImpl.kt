package ru.point.domain.usecase.implementation

import io.reactivex.rxjava3.core.Completable
import ru.point.domain.entity.dto.CreatedReviewDto
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.interfaces.AddReviewUseCase
import javax.inject.Inject

class AddReviewUseCaseImpl @Inject constructor(
    private val repository: ReviewRepository,
) : AddReviewUseCase {

    override fun handle(productId: Long, rating: Float, description: String): Completable {
        return repository.addReview(productId, CreatedReviewDto(rating, description))
    }
}