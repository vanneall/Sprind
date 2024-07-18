package ru.point.retrofit.api

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.domain.entity.response.order.OrderResponse
import ru.point.domain.entity.request.user.RegisterUserRequest
import ru.point.domain.entity.request.user.UserRequest
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.utils.Token

interface UserApi {

    @GET("auth")
    fun authorize(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Single<Token>

    @POST("auth")
    fun register(@Body user: RegisterUserRequest): Completable

    @GET("user")
    fun getUserInfo(): Single<UserRequest>

    @PATCH("user/address")
    fun setNewAddress(@Body address: AddressInfoResponse): Completable

    @GET("user/orders")
    fun getOrders(): Single<List<OrderResponse>>
}