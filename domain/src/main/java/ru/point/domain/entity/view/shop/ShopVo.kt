package ru.point.domain.entity.view.shop

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ImageUrlVo

data class ShopVo(
    val id: Long,
    val name: String,
    val photoUrl: ImageUrlVo
) : ViewObject