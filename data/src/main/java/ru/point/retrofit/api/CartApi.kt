package ru.point.retrofit.api


import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.point.domain.entity.response.cart.CartPageInfoResponse
import ru.point.domain.entity.response.product.FeedProductResponse

interface CartApi {

    @GET("cart/info")
    fun getPageInfo(): Single<CartPageInfoResponse>

    @GET("cart")
    fun getProductsFromCart(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<List<FeedProductResponse>>

    @PATCH("cart")
    fun addProduct(@Query("id") id: Long): Completable

    @PATCH("cart/order")
    fun makeOrder(): Completable

    @DELETE("cart")
    fun deleteFromCart(@Query("id") id: Long): Completable
}