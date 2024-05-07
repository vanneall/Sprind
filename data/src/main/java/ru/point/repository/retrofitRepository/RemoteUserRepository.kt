package ru.point.repository.retrofitRepository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.AuthUser
import ru.point.domain.entity.dto.RegisterUser
import ru.point.domain.entity.utils.Token
import ru.point.domain.repository.UserRepository
import ru.point.repository.retrofit.UserApi
import javax.inject.Inject

class RemoteUserRepository @Inject constructor(
    private val api: UserApi,
) : UserRepository {

    override fun authorize(user: AuthUser): Single<Token> {
        return api.authorize(user.username, user.password).subscribeOn(Schedulers.io())
    }

    override fun register(user: RegisterUser): Completable {
        return api.register(user = user).subscribeOn(Schedulers.io())
    }
}