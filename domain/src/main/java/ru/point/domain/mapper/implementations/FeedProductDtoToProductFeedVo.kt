package ru.point.domain.mapper.implementations

import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.entity.view.product.card.ProductFeedVo
import ru.point.domain.utils.StringFormatter

class FeedProductDtoToProductFeedVo {

    fun map(value: ProductFeedDto): ProductFeedVo {
        return ProductFeedVo(
            id = value.id,
            name = value.name,
            price = StringFormatter.formatPrice(value.price),
            isFavorite = value.isFavorite,
            description = value.description,
            photosUrl = value.photosUrl
        )
    }
}