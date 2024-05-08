package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.view.ViewObject

data class CharacteristicDescriptionVo(
    val name: String,
    val description: String,
) : ViewObject
