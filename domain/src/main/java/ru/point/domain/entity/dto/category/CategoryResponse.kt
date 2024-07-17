package ru.point.domain.entity.dto.category

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.dto.shop.ShopResponse

data class CategoryResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("shops")
    val shops: List<ShopResponse>
)
