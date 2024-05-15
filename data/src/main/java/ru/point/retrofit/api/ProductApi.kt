package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.ProductInfoDto

interface ProductApi {

    @GET("feed")
    fun getProductDto(): Observable<ComplexProductDto>

    @GET("feed")
    fun getProductDtoByName(@Query("name") search: String): Observable<ComplexProductDto>

    @GET("product/{id}")
    fun getProductDtoById(@Path("id") id: Long): Single<ProductInfoDto>
}