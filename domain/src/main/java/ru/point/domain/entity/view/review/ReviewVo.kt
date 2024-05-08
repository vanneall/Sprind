package ru.point.domain.entity.view.review

import ru.point.domain.entity.view.ViewObject

data class ReviewVo(
    val id: Long,
    val username: String,
    val rating: Float,
    val description: String,
) : ViewObject