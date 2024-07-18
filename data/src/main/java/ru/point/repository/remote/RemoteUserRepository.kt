package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.order.OrderResponse
import ru.point.domain.entity.request.user.AuthUserRequest
import ru.point.domain.entity.request.user.RegisterUserRequest
import ru.point.domain.entity.request.user.UserRequest
import ru.point.domain.entity.response.address.AddressInfoResponse
import ru.point.domain.entity.utils.Token
import ru.point.domain.repository.UserRepository
import ru.point.retrofit.api.UserApi

class RemoteUserRepository(
    private val api: UserApi,
) : UserRepository {

    override fun authorize(user: AuthUserRequest): Single<Token> {
        return api.authorize(user.username, user.password).subscribeOn(Schedulers.io())
    }

    override fun register(user: RegisterUserRequest): Completable {
        return api.register(user = user).subscribeOn(Schedulers.io())
    }

    override fun get(): Single<UserRequest> {
        return api.getUserInfo().subscribeOn(Schedulers.io())
    }

    override fun setNewAddress(address: AddressInfoResponse): Completable {
        return api.setNewAddress(address = address).subscribeOn(Schedulers.io())
    }

    override fun getOrders(): Single<List<OrderResponse>> {
        return api.getOrders().subscribeOn(Schedulers.io())
    }
}