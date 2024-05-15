package ru.point.domain.mapper.implementations

import ru.point.domain.entity.complex.ComplexProductCartVoContainer
import ru.point.domain.entity.dto.complex.ComplexProductDto

class ComplexDtoToCartVoMapper {

    private val mapper = ComplexProductDtoForCartMapper()

    fun map(complexProductDto: ComplexProductDto): ComplexProductCartVoContainer {
        return ComplexProductCartVoContainer(
            productsVo = mapper.map(complexProductDto),
            isAddressEmpty = complexProductDto.addressDto == null
        )
    }
}