package ru.point.domain.entity.request.review

import com.google.gson.annotations.SerializedName

data class CreateReviewRequest(
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("description")
    val description: String,
    @SerializedName("advantage")
    val advantage: String?,
    @SerializedName("disadvantage")
    val disadvantage: String?,
)
