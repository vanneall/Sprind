package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.point.domain.entity.utils.Token

interface UserApi {

    @GET("auth")
    fun authorize(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Single<Token>
}