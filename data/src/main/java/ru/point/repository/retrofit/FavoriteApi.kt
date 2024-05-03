package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import ru.point.domain.entity.dto.FeedProductDto


interface FavoriteApi {
    @GET("favorites")
    fun getFavorites(@Header("Authorization") token: String = toke1n): Observable<List<FeedProductDto>>
}