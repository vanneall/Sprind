package ru.point.domain.entity.view.category

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ImageUrlVo

data class CategoryVo(
    val id: Long,
    val name: String,
    val photoUrl: ImageUrlVo
) : ViewObject