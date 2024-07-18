package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.response.complex.ComplexProductResponse
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.entity.response.product.ProductInfoResponse

interface ProductApi {
    @GET("feed/info")
    fun getPageInfo(): Single<ComplexProductResponse>

    @GET("feed")
    fun getProductDto(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("name") search: String? = null
    ): Single<List<FeedProductResponse>>

    @GET("product/{id}")
    fun getProductDtoById(@Path("id") id: Long): Single<ProductInfoResponse>
}