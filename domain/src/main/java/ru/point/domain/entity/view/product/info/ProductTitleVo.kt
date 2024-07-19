package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.view.ViewObject

data class ProductTitleVo(
    val title: String,
    val price: String,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val rating: String,
) : ViewObject