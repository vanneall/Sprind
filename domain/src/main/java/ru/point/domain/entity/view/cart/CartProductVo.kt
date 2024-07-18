package ru.point.domain.entity.view.cart

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ImageUrlVo

data class CartProductVo(
    val id: Long,
    val name: String,
    val imageUrl: ImageUrlVo,
    val price: String,
    val isFavorite: Boolean
) : ViewObject