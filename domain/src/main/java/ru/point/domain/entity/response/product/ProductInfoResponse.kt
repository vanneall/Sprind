package ru.point.domain.entity.response.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.utils.price.Price

data class ProductInfoResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("price")
    val price: Price,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("isInCart")
    val isInCart: Boolean,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("characteristics")
    val productCharacteristicResponses: Set<ProductCharacteristicResponse>,
    @SerializedName("photosUrl")
    val photosUrl: List<String>,
    @SerializedName("shop")
    val shopDto: ProductShopResponse,
    @SerializedName("category")
    val categoryDto: ProductCategoryResponse,
    @SerializedName("rating")
    val rating: Float,
): ResponseItem