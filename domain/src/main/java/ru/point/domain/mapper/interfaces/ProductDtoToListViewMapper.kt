package ru.point.domain.mapper.interfaces

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.dto.product.ProductInfoDto

interface ProductDtoToListViewMapper {
    fun map(productInfoDto: ProductInfoDto): List<ViewObject>
}