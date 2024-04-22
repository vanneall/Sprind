package ru.point.domain.usecase.interfaces

import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductDto

interface ProductDtoToListViewMapper {
    fun map(productDto: ProductDto): List<ListView>
}