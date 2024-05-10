package ru.point.retrofit.api


import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.point.domain.entity.dto.product.ProductFeedDto

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(): Observable<List<ProductFeedDto>>

    @PATCH("cart")
    fun addProduct(@Query("id") id: Long): Completable

    @PATCH("cart/order")
    fun makeOrder(): Completable
}