package ru.point.domain.entity.view.cart

import ru.point.domain.entity.view.ViewObject

data class CartProductVo(
    val id: Long,
    val name: String,
    val url: String,
    val price: String,
    val isFavorite: Boolean
): ViewObject