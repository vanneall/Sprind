package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.domain.entity.dto.product.ProductFeedDto


interface FavoriteApi {

    @GET("favorites")
    fun getFavorites(): Observable<List<ProductFeedDto>>

    @POST("favorites")
    fun putFavorites(@Query(value = "productId") id: Long): Observable<List<String>>

    @DELETE("favorites")
    fun deleteFavorites(@Query(value = "productId") id: Long): Observable<List<String>>
}