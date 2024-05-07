package ru.point.repository.retrofit


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import ru.point.domain.entity.dto.FeedProductDto

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(): Observable<List<FeedProductDto>>

    @PUT("cart/add")
    fun addProduct(@Query("productId") id: Long): Observable<String>
}