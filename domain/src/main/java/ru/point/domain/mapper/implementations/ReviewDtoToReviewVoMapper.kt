package ru.point.domain.mapper.implementations

import ru.point.domain.entity.dto.review.ReviewDto
import ru.point.domain.entity.view.review.ReviewVo

class ReviewDtoToReviewVoMapper private constructor() {

    companion object {
        fun map(dto: ReviewDto): ReviewVo {
            return ReviewVo(
                id = dto.id,
                username = dto.username,
                rating = dto.rating,
                description = dto.description,
                advantages = dto.advantage,
                disadvantages = dto.disadvantage
            )
        }
    }
}