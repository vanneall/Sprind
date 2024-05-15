package ru.point.domain.mapper.implementations

import ru.point.domain.entity.complex.ComplexProductFeedVoContainer
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.utils.StringFormatter

class ComplexDtoToViewObjectMapper {

    private val productMapper = FeedProductDtoToProductFeedVo()

    fun map(complexDto: ComplexProductDto): ComplexProductFeedVoContainer {
        val products = complexDto.productDto.map(productMapper::map)
        val address = AddressVo(StringFormatter.formatAddress(addressDto = complexDto.addressDto))
        return ComplexProductFeedVoContainer(
            addressVo = address,
            productsVo = products
        )
    }
}