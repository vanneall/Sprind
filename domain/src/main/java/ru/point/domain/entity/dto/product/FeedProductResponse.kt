package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.domain.entity.view.product.card.FeedProductVo
import ru.point.domain.utils.StringFormatter
import ru.point.domain.utils.UtilsConst.PICTURE_NOT_FOUND

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
    @SerializedName("photosUrl")
    val photosUrl: List<String>
) : ResponseItem

fun FeedProductResponse.toProductFeedVo(): FeedProductVo {
    return FeedProductVo(
        id = id,
        name = name,
        price = StringFormatter.formatPrice(price),
        isFavorite = isFavorite,
        isInCart = isInCart,
        description = description,
        photosUrl = photosUrl
    )
}

fun FeedProductResponse.toCartProductVo(): CartProductVo {
    return CartProductVo(
        id = id,
        name = name,
        price = StringFormatter.formatPrice(price),
        isFavorite = isFavorite,
        url = if (photosUrl.isNotEmpty()) photosUrl.first() else PICTURE_NOT_FOUND
    )
}
