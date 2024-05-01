package ru.point.domain.usecase.interfaces

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.dto.ProductDto

interface ProductDtoToListViewMapper {
    fun map(productDto: ProductDto): List<ViewObject>
}