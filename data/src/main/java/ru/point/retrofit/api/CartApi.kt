package ru.point.retrofit.api


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import ru.point.domain.entity.dto.product.ProductFeedDto

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(): Observable<List<ProductFeedDto>>

    @PUT("cart/add")
    fun addProduct(@Query("productId") id: Long): Observable<String>
}