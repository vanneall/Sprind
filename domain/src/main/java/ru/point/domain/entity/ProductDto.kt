package ru.point.domain.entity

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("price")
    val price: Price,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("characteristics")
    val characteristics: Map<String, String>,
    @SerializedName("photosUrl")
    val photosUrl: List<String>,
    @SerializedName("shop")
    val shopDto: ProductShopDto,
    @SerializedName("category")
    val categoryDto: ProductCategoryDto,
)