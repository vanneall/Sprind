package ru.point.domain.entity.view

import ru.point.domain.entity.utils.Price

data class CartProductVo(
    val id: Long,
    val name: String,
    val url: String,
    val price: Price
): ViewObject