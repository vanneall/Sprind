package ru.point.domain.entity.response.category

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.response.shop.ShopResponse

data class CategoryResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("shops")
    val shops: List<ShopResponse>
): ResponseItem