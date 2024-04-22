package ru.point.domain.entity

import com.google.gson.annotations.SerializedName

data class ProductCategoryDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("parentCatrgory")
    val parentCategoryDto: ProductCategoryDto? = null
)
