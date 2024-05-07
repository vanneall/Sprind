package ru.point.domain.entity.dto

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.ProductShopDto

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
    val characteristics: Set<Characteristic>,
    @SerializedName("photosUrl")
    val photosUrl: List<String>,
    @SerializedName("shop")
    val shopDto: ProductShopDto,
    @SerializedName("category")
    val categoryDto: ProductCategoryDto,
)

data class Characteristic(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    private val description: String,
) {
    val elements: List<Pair<String, String>>
        get() {
            val list = mutableListOf<Pair<String, String>>()
            description
                .split(";")
                .filter { s -> s.isNotEmpty() }
                .forEach { s ->
                    list.add(Pair(s.substringBefore(':'), s.substringAfter(':')))
                }
            return list
        }
}