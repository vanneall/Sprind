package ru.point.domain.entity.response.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

data class ProductCategoryResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
): ResponseItem