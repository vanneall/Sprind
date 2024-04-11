package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.point.domain.entity.Product

interface ProductApi {
    @GET("product")
    fun getProductDto(): Observable<List<Product>>

    @GET("product")
    fun getProductDtoByName(@Query("name") search: String): Observable<List<Product>>
}