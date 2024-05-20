package ru.point.domain.entity.view.order

import ru.point.domain.entity.view.ViewObject

data class OrderVo(
    val id: Long,
    val deliveryCost: String,
    val productsCost: String,
    val summaryCost: String
): ViewObject