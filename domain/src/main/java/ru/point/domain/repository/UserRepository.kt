package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.order.OrderResponse
import ru.point.domain.entity.request.user.AuthUserRequest
import ru.point.domain.entity.request.user.RegisterUserRequest
import ru.point.domain.entity.request.user.UserRequest
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.utils.Token

interface UserRepository {

    fun authorize(user: AuthUserRequest): Single<Token>

    fun register(user: RegisterUserRequest): Completable

    fun get(): Single<UserRequest>

    fun setNewAddress(address: AddressInfoResponse): Completable

    fun getOrders(): Single<List<OrderResponse>>
}