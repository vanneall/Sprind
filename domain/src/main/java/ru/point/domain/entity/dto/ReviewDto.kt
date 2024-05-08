package ru.point.domain.entity.dto

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Float
)
