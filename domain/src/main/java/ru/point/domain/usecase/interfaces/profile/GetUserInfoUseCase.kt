package ru.point.domain.usecase.interfaces.profile

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.user.UserDto

interface GetUserInfoUseCase {
    fun handle(): Single<UserDto>
}