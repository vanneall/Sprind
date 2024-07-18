package ru.point.domain.usecase.implementation.profile

import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.profile.GetUserInfoUseCase

class GetUserInfoUseCaseImpl(
    private val repository: UserRepository
): GetUserInfoUseCase {
    override fun handle() = repository.get()
}