package ru.point.repository.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.domain.entity.dto.RegisterUser
import ru.point.domain.entity.utils.Token

interface UserApi {

    @GET("auth")
    fun authorize(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Single<Token>

    @POST("auth")
    fun register(@Body user: RegisterUser): Completable
}