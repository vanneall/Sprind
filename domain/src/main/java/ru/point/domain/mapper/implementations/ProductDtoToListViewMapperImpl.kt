package ru.point.domain.mapper.implementations

import ru.point.domain.entity.dto.product.ProductInfoDto
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.AllCharacteristicsVo
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.entity.view.product.info.ProductDescriptionVo
import ru.point.domain.entity.view.product.info.ProductImageVo
import ru.point.domain.entity.view.product.info.ProductReviewVo
import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.domain.mapper.interfaces.ProductDtoToListViewMapper
import ru.point.domain.utils.StringFormatter

class ProductDtoToListViewMapperImpl : ProductDtoToListViewMapper {
    override fun map(productInfoDto: ProductInfoDto): List<ViewObject> {
        return listOf(
            NestedRecyclerViewVo(
                viewObjects = productInfoDto.photosUrl.map { url -> ProductImageVo(url) }
            ),
            ProductTitleVo(
                title = productInfoDto.name,
                isFavorite = productInfoDto.isFavorite,
                price = StringFormatter.formatPrice(productInfoDto.price),
            ),
            ProductDescriptionVo(
                description = productInfoDto.description ?: "",
                shopName = productInfoDto.shopDto.name,
                categoryName = productInfoDto.categoryDto.name
            ),
            AllCharacteristicsVo(
                productCharacteristicDtos = productInfoDto.productCharacteristicDtos.toList()
            ),
            ProductReviewVo()
        )
    }
}