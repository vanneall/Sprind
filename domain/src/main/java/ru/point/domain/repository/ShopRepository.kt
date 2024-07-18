package ru.point.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.product.FeedProductResponse

interface ShopRepository {
    fun getShopProducts(offset: Int, limit: Int, shopId: Long): Single<List<FeedProductResponse>>
}