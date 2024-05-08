package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.view.ViewObject

data class ProductDescriptionVo(
    val description: String,
    val shopName: String,
    val categoryName: String,
) : ViewObject