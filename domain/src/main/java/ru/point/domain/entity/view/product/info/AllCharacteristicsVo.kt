package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.dto.product.ProductCharacteristicDto
import ru.point.domain.entity.view.ViewObject


data class AllCharacteristicsVo(
    val productCharacteristicDtos: List<ProductCharacteristicDto>,
) : ViewObject