package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.order.OrderDto
import ru.point.domain.entity.dto.user.AuthUserDto
import ru.point.domain.entity.dto.user.RegisterUserDto
import ru.point.domain.entity.dto.user.UserDto
import ru.point.domain.entity.utils.AddressDto
import ru.point.domain.entity.utils.Token
import ru.point.domain.repository.UserRepository
import ru.point.retrofit.api.UserApi

class RemoteUserRepository(
    private val api: UserApi,
) : UserRepository {

    override fun authorize(user: AuthUserDto): Single<Token> {
        return api.authorize(user.username, user.password).subscribeOn(Schedulers.io())
    }

    override fun register(user: RegisterUserDto): Completable {
        return api.register(user = user).subscribeOn(Schedulers.io())
    }

    override fun get(): Single<UserDto> {
        return api.getUserInfo().subscribeOn(Schedulers.io())
    }

    override fun setNewAddress(address: AddressDto): Completable {
        return api.setNewAddress(address = address).subscribeOn(Schedulers.io())
    }

    override fun getOrders(): Single<List<OrderDto>> {
        return api.getOrders().subscribeOn(Schedulers.io())
    }
}