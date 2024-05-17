package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.product.info.AllCharacteristicsVo
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.entity.view.product.info.ProductDescriptionVo
import ru.point.domain.entity.view.product.info.ProductImageVo
import ru.point.domain.entity.view.product.info.ProductReviewVo
import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.domain.utils.StringFormatter

data class ProductInfoDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("price")
    val price: Price,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
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

fun ProductInfoDto.toNestedRecyclerViewVo(): NestedRecyclerViewVo {
    return NestedRecyclerViewVo(
        viewObjects = photosUrl.map { url -> ProductImageVo(url) }
    )
}

fun ProductInfoDto.toProductTitleVo(): ProductTitleVo {
    return ProductTitleVo(
        title = name,
        isFavorite = isFavorite,
        price = StringFormatter.formatPrice(price),
    )
}

fun ProductInfoDto.toProductDescriptionVo(): ProductDescriptionVo {
    return ProductDescriptionVo(
        description = description ?: "",
        shopName = shopDto.name,
        categoryName = categoryDto.name
    )
}

fun ProductInfoDto.toAllCharacteristicsVo(): AllCharacteristicsVo {
    return AllCharacteristicsVo(
        productCharacteristicDtos = productCharacteristicDtos.toList()
    )
}

fun ProductInfoDto.toProductReviewVo(): ProductReviewVo {
    return ProductReviewVo()
}