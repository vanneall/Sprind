package ru.point.domain.entity.view.order

import ru.point.domain.entity.view.ViewObject

data class OrderVo(
    val id: Long,
    val deliveryPrice: String,
    val productsPrice: String,
    val summaryPrice: String
) : ViewObject