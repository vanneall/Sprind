package ru.point.domain.entity.complex

import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.entity.view.cart.CartSummaryVo

data class ComplexProductFeedVo(
    val addressVo: AddressVo
)

data class CartInfoVoContainer(
    val addressVo: AddressVo?,
    val orderSummaryVo: CartSummaryVo?
)