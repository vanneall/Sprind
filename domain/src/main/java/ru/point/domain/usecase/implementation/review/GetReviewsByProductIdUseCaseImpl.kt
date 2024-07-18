package ru.point.domain.usecase.implementation.review

import ru.point.domain.entity.response.mappers.toReviewVo
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.interfaces.review.GetReviewsByProductIdUseCase

class GetReviewsByProductIdUseCaseImpl(
    private val repository: ReviewRepository,
) : GetReviewsByProductIdUseCase {
    override fun handle(id: Long) = repository.getByProductId(id)
        .map { list ->
            list.map { reviewDto -> reviewDto.toReviewVo() }
        }
}