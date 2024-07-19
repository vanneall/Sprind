package ru.point.domain.entity.view.product.card

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ImageUrlVo

data class FeedProductVo(
    val id: Long,
    val name: String,
    val price: String,
    val isFavorite: Boolean,
    val inCart: Boolean,
    val rating: Float,
    val description: String? = null,
    val imagesUrl: List<ImageUrlVo>
) : ViewObject