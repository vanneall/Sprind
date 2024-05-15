package ru.point.domain.entity.complex

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartEmptyVo

data class ComplexProductCartVoContainer(
    val productsVo: List<ViewObject>
) {
    val isEmptyCartVo get() = if (productsVo.isEmpty()) CartEmptyVo() else null
}
