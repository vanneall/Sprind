package ru.point.domain.entity

import com.google.gson.annotations.SerializedName

data class ProductShopDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)
