package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.dto.product.FeedProductResponse

interface ShopApi {
    @GET("/shops/{id}")
    fun getShopsProducts(
        @Path("id") shopId: Long,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<List<FeedProductResponse>>
}