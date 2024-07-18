package ru.point.domain.entity.response.review

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

data class ReviewResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("advantage")
    val advantage: String? = null,
    @SerializedName(value = "disadvantage")
    val disadvantage: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("rating")
    val rating: Float,
): ResponseItem