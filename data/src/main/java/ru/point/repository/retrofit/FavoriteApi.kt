package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.domain.entity.dto.FeedProductDto


interface FavoriteApi {
    @GET("favorites")
    fun getFavorites(@Header("Authorization") token: String = toke1n): Observable<List<FeedProductDto>>

    @POST("favorites")
    fun putFavorites(
        @Header("Authorization") token: String = toke1n,
        @Query(value = "productId") id: Long,
    ): Observable<List<String>>

    @DELETE("favorites")
    fun deleteFavorites(
        @Header("Authorization") token: String = toke1n,
        @Query(value = "productId") id: Long,
    ): Observable<List<String>>
}