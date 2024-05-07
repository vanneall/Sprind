package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.entity.dto.ProductDto

interface ProductApi {

    @GET("feed")
    fun getProductDto(): Observable<List<FeedProductDto>>

    @GET("feed")
    fun getProductDtoByName(@Query("name") search: String): Observable<List<FeedProductDto>>

    @GET("product/{id}")
    fun getProductDtoById(@Path("id") id: Long): Single<ProductDto>
}