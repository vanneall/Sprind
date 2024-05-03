package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.entity.dto.ProductDto


//TODO написать отдельные методы для авторизованных/неавторизованных пользователей
interface ProductApi {
    @GET("feed")
    fun getProductDto(@Header("Authorization") token: String = toke1n): Observable<List<FeedProductDto>>

    @GET("feed")
    fun getProductDtoByName(
        @Header("Authorization") token: String = toke1n,
        @Query("name") search: String,
    ): Observable<List<FeedProductDto>>

    @GET("product/{id}")
    fun getProductDtoById(
        @Header("Authorization") token: String = toke1n,
        @Path("id") id: Long,
    ): Single<ProductDto>
}