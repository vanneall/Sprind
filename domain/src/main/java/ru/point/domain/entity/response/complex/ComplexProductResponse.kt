package ru.point.domain.entity.response.complex

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.response.product.FeedProductResponse

data class ComplexProductResponse(
    @SerializedName(value = "address")
    val addressInfoResponse: AddressInfoResponse?,
    @SerializedName(value = "products")
    val productDto: List<FeedProductResponse>,
): ResponseItem