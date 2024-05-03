package ru.point.domain.mapper

import ru.point.domain.entity.dto.ProductDto
import ru.point.domain.entity.view.AllCharacteristicsViewObject
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.ProductDescriptionViewObject
import ru.point.domain.entity.view.ProductImageViewObject
import ru.point.domain.entity.view.ProductTitleViewObject
import ru.point.domain.usecase.interfaces.ProductDtoToListViewMapper

class ProductDtoToListViewMapperImpl : ProductDtoToListViewMapper {
    override fun map(productDto: ProductDto): List<ViewObject> {
        return listOf(
            ProductImageViewObject(
                url = productDto.photosUrl[0]
            ),
            ProductTitleViewObject(
                title = productDto.name,
                price = productDto.price,
            ),
            ProductDescriptionViewObject(
                description = productDto.description ?: "",
                shopName = productDto.shopDto.name,
                categoryName = productDto.categoryDto.name
            ),
            AllCharacteristicsViewObject(
                characteristics = productDto.characteristics.toList()
            )
        )
    }
}