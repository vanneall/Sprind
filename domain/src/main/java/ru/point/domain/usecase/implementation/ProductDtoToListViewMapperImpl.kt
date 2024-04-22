package ru.point.domain.usecase.implementation

import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductDescription
import ru.point.domain.entity.ProductDto
import ru.point.domain.entity.ProductImage
import ru.point.domain.entity.ProductTitle
import ru.point.domain.usecase.interfaces.ProductDtoToListViewMapper

class ProductDtoToListViewMapperImpl : ProductDtoToListViewMapper {
    override fun map(productDto: ProductDto): List<ListView> {
        return listOf(
            ProductImage(
                url = productDto.photosUrl[0]
            ),
            ProductTitle(
                title = productDto.name,
                price = productDto.price
            ),
            ProductDescription(
                description = productDto.description ?: ""
            )
        )
    }
}