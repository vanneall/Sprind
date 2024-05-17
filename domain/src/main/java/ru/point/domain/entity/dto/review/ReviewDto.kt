package ru.point.domain.entity.dto.review

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.view.review.ReviewVo

data class ReviewDto(
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
)

fun ReviewDto.toReviewVo(): ReviewVo {
    return ReviewVo(
        id = id,
        username = username,
        rating = rating,
        description = description,
        advantages = advantage,
        disadvantages = disadvantage
    )
}
