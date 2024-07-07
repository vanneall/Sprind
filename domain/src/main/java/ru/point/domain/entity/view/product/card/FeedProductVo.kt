package ru.point.domain.entity.view.product.card

import ru.point.domain.entity.view.ViewObject

data class FeedProductVo(
    val id: Long,
    val name: String,
    val price: String,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val description: String? = null,
    val photosUrl: List<String>
) : ViewObject