package ru.point.domain.entity.dto.complex

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.entity.utils.AddressDto

data class ComplexProductDto(
    @SerializedName(value = "address")
    val addressDto: AddressDto?,
    @SerializedName(value = "products")
    val productDto: List<ProductFeedDto>,
)