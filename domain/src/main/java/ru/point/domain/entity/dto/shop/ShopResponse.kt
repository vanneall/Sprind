package ru.point.domain.entity.dto.shop

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.entity.view.shop.ShopVo

data class ShopResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo_url")
    val photoUrl: String?
) : ResponseItem

fun ShopResponse.toShopVo(): ShopVo {
    return ShopVo(
        id = id,
        name = name,
        photoUrl = photoUrl
    )
}