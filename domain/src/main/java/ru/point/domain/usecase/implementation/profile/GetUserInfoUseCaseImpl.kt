package ru.point.domain.usecase.implementation.profile

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.user.UserDto
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.profile.GetUserInfoUseCase

class GetUserInfoUseCaseImpl(
    private val repository: UserRepository
): GetUserInfoUseCase {
    override fun handle(): Single<UserDto> {
        return repository.get()
    }
}