package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.utils.Price
import ru.point.domain.entity.view.ViewObject

data class ProductTitleVo(
    val title: String,
    val price: Price,
    val rating: String = "5.0",
) : ViewObject