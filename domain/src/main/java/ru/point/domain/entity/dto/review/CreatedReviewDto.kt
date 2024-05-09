package ru.point.domain.entity.dto.review

import com.google.gson.annotations.SerializedName

data class CreatedReviewDto(
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("description")
    val description: String
)