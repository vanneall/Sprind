package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.domain.entity.view.product.card.ProductFeedVo
import ru.point.domain.utils.StringFormatter

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

fun ProductFeedDto.toProductFeedVo(): ProductFeedVo {
    return ProductFeedVo(
        id = id,
        name = name,
        price = StringFormatter.formatPrice(price),
        isFavorite = isFavorite,
        description = description,
        photosUrl = photosUrl
    )
}

fun ProductFeedDto.toCartProductVo(): CartProductVo {
    return CartProductVo(
        id = id,
        name = name,
        price = StringFormatter.formatPrice(price),
        isFavorite = isFavorite,
        url = photosUrl.first()
    )
}
