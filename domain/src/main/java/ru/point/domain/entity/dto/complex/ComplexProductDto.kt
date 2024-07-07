package ru.point.domain.entity.dto.complex

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.complex.ComplexProductCartVoContainer
import ru.point.domain.entity.complex.ComplexProductFeedVo
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.utils.AddressInfoResponse
import ru.point.domain.entity.utils.toAddressVo
import ru.point.domain.mapper.implementations.ComplexProductDtoForCartMapper

data class ComplexProductDto(
    @SerializedName(value = "address")
    val addressInfoResponse: AddressInfoResponse?,
    @SerializedName(value = "products")
    val productDto: List<FeedProductResponse>,
)

fun ComplexProductDto.toComplexProductFeedVo(): ComplexProductFeedVo {
    val address = addressInfoResponse.toAddressVo()

    return ComplexProductFeedVo(
        addressVo = address
    )
}

fun ComplexProductDto.toComplexProductCartVoContainer() : ComplexProductCartVoContainer {
    return ComplexProductCartVoContainer(
        productsVo = ComplexProductDtoForCartMapper.map(this),
        isAddressEmpty = addressInfoResponse == null
    )
}