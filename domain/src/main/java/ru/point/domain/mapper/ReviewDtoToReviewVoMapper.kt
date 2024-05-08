package ru.point.domain.mapper

import ru.point.domain.entity.dto.ReviewDto
import ru.point.domain.entity.view.ReviewVo

class ReviewDtoToReviewVoMapper private constructor() {

    fun map(dto: ReviewDto): ReviewVo {
        return ReviewVo(
            dto.id,
            dto.username,
            dto.rating,
            dto.description
        )
    }
}