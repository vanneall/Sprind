package ru.point.domain.entity

import com.google.gson.annotations.SerializedName

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
    val photosUrl: List<String>,
    var onClick: (() -> Unit)? = null,
) : ListView
