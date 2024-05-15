package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.domain.entity.dto.user.RegisterUserDto
import ru.point.domain.entity.dto.user.UserDto
import ru.point.domain.entity.utils.AddressDto
import ru.point.domain.entity.utils.Token

interface UserApi {

    @GET("auth")
    fun authorize(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Single<Token>

    @POST("auth")
    fun register(@Body user: RegisterUserDto): Completable

    @GET("user")
    fun getUserInfo(): Single<UserDto>

    @PATCH("user/address")
    fun setNewAddress(@Body address: AddressDto): Completable
}