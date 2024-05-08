package ru.point.domain.entity.view.cart

import ru.point.domain.entity.view.ViewObject

data class CartSummaryVo(
    val delivery: String,
    val products: String,
    val discount: String,
    val promocode: String,
    val summary: String,
) : ViewObject