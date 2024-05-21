package ru.point.domain.entity.complex

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartEmptyVo

data class ComplexProductCartVoContainer(
    val productsVo: List<ViewObject>,
    val isAddressEmpty: Boolean
) {
    val isCartEmpty get() = productsVo.first() != CartEmptyVo()
}
