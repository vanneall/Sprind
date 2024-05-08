package ru.point.domain.entity.view

data class ReviewVo(
    val id: Long,
    val username: String,
    val rating: Float,
    val description: String,
) : ViewObject