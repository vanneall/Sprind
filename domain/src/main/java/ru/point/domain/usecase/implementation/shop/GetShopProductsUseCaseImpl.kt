package ru.point.domain.usecase.implementation.shop

import ru.point.domain.paging.ShopPagingSource
import ru.point.domain.usecase.interfaces.shop.GetShopProductsUseCase

class GetShopProductsUseCaseImpl(
    private val pagingSourceFactory: ShopPagingSource.Factory
) : GetShopProductsUseCase {
    override fun handle(shopId: Long) = pagingSourceFactory.create(shopId = shopId)
}