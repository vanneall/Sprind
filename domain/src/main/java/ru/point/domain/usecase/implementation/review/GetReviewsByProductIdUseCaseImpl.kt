package ru.point.domain.usecase.implementation.review

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.review.toReviewVo
import ru.point.domain.entity.view.review.ReviewVo
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.interfaces.review.GetReviewsByProductIdUseCase

class GetReviewsByProductIdUseCaseImpl(
    private val repository: ReviewRepository,
) : GetReviewsByProductIdUseCase {
    override fun handle(id: Long): Observable<List<ReviewVo>> {
        return repository.getByProductId(id).map { list ->
            list.map { reviewDto -> reviewDto.toReviewVo()}
        }
    }
}