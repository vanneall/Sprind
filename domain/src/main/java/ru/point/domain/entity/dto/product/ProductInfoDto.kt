package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Price

data class ProductInfoDto(
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
    val productCharacteristicDtos: Set<ProductCharacteristicDto>,
    @SerializedName("photosUrl")
    val photosUrl: List<String>,
    @SerializedName("shop")
    val shopDto: ProductShopDto,
    @SerializedName("category")
    val categoryDto: ProductCategoryDto,
)