package ru.point.domain.usecase.interfaces.profile

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.request.user.UserRequest

interface GetUserInfoUseCase {
    fun handle(): Single<UserRequest>
}