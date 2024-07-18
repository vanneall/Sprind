package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.point.domain.entity.response.product.FeedProductResponse


interface FavoriteApi {

    @GET("favorites")
    fun getFavorites(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<List<FeedProductResponse>>

    @PATCH("favorites")
    fun putFavorites(@Query(value = "id") id: Long): Completable

    @DELETE("favorites")
    fun deleteFavorites(@Query(value = "id") id: Long): Completable
}