package ru.point.repository.remote

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.repository.ShopRepository
import ru.point.retrofit.api.ShopApi

class ShopRepositoryImpl(
    private val api: ShopApi
) : ShopRepository {
    override fun getShopProducts(
        offset: Int,
        limit: Int,
        shopId: Long
    ): Single<List<FeedProductResponse>> {
        return api.getShopsProducts(
            shopId = shopId,
            offset = offset,
            limit = limit
        ).subscribeOn(Schedulers.io())
    }
}