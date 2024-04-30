package ru.point.domain.usecase.interfaces

import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.dto.ProductDto

interface ProductDtoToListViewMapper {
    fun map(productDto: ProductDto): List<ListView>
}