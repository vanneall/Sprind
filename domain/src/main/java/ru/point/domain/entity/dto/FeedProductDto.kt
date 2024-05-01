package ru.point.domain.entity.dto

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.Price
import ru.point.domain.entity.view.ViewObject

@JvmInline
value class ImageURL(
    val url: String,
)

data class FeedProductDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("photosUrl")
    val photosUrl: List<String>
) : ViewObject
