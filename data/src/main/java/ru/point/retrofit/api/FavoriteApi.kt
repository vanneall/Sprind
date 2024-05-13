package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.point.domain.entity.dto.product.ProductFeedDto


interface FavoriteApi {

    @GET("favorites")
    fun getFavorites(): Observable<List<ProductFeedDto>>

    @PATCH("favorites")
    fun putFavorites(@Query(value = "id") id: Long): Completable

    @DELETE("favorites")
    fun deleteFavorites(@Query(value = "id") id: Long): Completable
}