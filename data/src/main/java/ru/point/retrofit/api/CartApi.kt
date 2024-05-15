package ru.point.retrofit.api


import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.point.domain.entity.dto.complex.ComplexProductDto

interface CartApi {

    @GET("cart")
    fun getProductsFromCart(): Observable<ComplexProductDto>

    @PATCH("cart")
    fun addProduct(@Query("id") id: Long): Completable

    @PATCH("cart/order")
    fun makeOrder(): Completable

    @DELETE("cart")
    fun deleteFromCart(@Query("id") id: Long): Completable
}