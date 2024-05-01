package ru.point.domain.mapper

import ru.point.domain.entity.dto.ProductDto
import ru.point.domain.entity.view.AllCharacteristicsView
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductDescriptionView
import ru.point.domain.entity.view.ProductImageView
import ru.point.domain.entity.view.ProductTitleView
import ru.point.domain.usecase.interfaces.ProductDtoToListViewMapper

class ProductDtoToListViewMapperImpl : ProductDtoToListViewMapper {
    override fun map(productDto: ProductDto): List<ListView> {
        return listOf(
            ProductImageView(
                url = productDto.photosUrl[0]
            ),
            ProductTitleView(
                title = productDto.name,
                price = productDto.price
            ),
            ProductDescriptionView(
                description = productDto.description ?: "",
                shopName = productDto.shopDto.name,
                categoryName = productDto.categoryDto.name
            ),
            AllCharacteristicsView(
                characteristics = productDto.characteristics.toList()
            )
        )
    }
}