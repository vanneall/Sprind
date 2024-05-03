package ru.point.repository.retrofit


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query
import ru.point.domain.entity.dto.FeedProductDto

const val toke1n =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZWZveHl3ZWV4QGdtYWlsLmNvbSIsImV4cCI6MTcxNDgxMzYwMSwiaWF0IjoxNzE0NzI3MjAxLCJhdXRob3JpdHkiOlsiUk9MRV9VU0VSIl19.HcxXsxuvBiskIs3y9DWwGorN5W5U7E24nZ2FwrGHCMU"

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(@Header("Authorization") token: String = toke1n): Observable<List<FeedProductDto>>

    @PUT("cart/add")
    fun addProduct(@Header("Authorization") token: String = toke1n, @Query("productId") id: Long): Observable<String>
}