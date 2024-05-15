package ru.point.domain.entity.complex

import ru.point.domain.entity.view.address.AddressVo
import ru.point.domain.entity.view.product.card.ProductFeedVo

data class ComplexProductFeedVoContainer(
    val addressVo: AddressVo,
    val productsVo: List<ProductFeedVo>
)