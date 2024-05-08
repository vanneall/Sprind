package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName

data class ProductShopDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
)
