package ru.point.domain.usecase.implementation.shop

import ru.point.domain.repository.ShopRepository
import ru.point.domain.usecase.interfaces.shop.GetShopProductsUseCase

class GetShopProductsUseCaseImpl(
    private val repository: ShopRepository
) : GetShopProductsUseCase {
    override fun handle(shopId: Long) = repository.getShopProducts(shopId)
}