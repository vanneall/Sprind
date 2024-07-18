package ru.point.domain.entity.response.shop

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

data class ShopResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo_url")
    val photoUrl: String?
) : ResponseItem