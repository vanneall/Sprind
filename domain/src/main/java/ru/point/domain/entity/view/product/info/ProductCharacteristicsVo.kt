package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.response.product.ProductCharacteristicResponse
import ru.point.domain.entity.view.ViewObject


data class ProductCharacteristicsVo(
    val characteristicsResponse: List<ProductCharacteristicResponse>,
) : ViewObject