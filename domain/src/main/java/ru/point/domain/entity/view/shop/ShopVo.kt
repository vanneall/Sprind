package ru.point.domain.entity.view.shop

import ru.point.domain.entity.view.ViewObject

data class ShopVo(
    val id: Long,
    val name: String,
    val photoUrl: String?
): ViewObject