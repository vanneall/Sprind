package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.ViewObject

data class ProductFeedDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("photosUrl")
    val photosUrl: List<String>
) : ViewObject
