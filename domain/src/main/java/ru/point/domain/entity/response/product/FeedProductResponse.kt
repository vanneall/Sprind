package ru.point.domain.entity.response.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.utils.price.Price

data class FeedProductResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("isInCart")
    val isInCart: Boolean,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("photosUrl")
    val imagesUrl: List<String>
) : ResponseItem