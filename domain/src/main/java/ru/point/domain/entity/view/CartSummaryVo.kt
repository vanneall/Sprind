package ru.point.domain.entity.view

data class CartSummaryVo(
    val delivery: String,
    val products: String,
    val discount: String,
    val promocode: String,
    val summary: String,
) : ViewObject