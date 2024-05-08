package ru.point.domain.usecase.implementation.review

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.review.ReviewVo
import ru.point.domain.mapper.implementations.ReviewDtoToReviewVoMapper
import ru.point.domain.repository.ReviewRepository
import ru.point.domain.usecase.interfaces.review.GetReviewsByProductIdUseCase
import javax.inject.Inject

class GetReviewsByProductIdUseCaseImpl @Inject constructor(
    private val repository: ReviewRepository,
) : GetReviewsByProductIdUseCase {
    override fun handle(id: Long): Observable<List<ReviewVo>> {
        return repository.getByProductId(id).map {
            it.map {
                ReviewDtoToReviewVoMapper.map(it)
            }
        }
    }
}