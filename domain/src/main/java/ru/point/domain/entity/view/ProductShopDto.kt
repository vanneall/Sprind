package ru.point.domain.entity.view

import com.google.gson.annotations.SerializedName

data class ProductShopDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
)
