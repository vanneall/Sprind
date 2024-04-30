package ru.point.domain.entity.dto

import com.google.gson.annotations.SerializedName

data class ProductCategoryDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
)
