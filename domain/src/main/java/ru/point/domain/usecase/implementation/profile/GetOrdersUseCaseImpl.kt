package ru.point.domain.usecase.implementation.profile

import ru.point.domain.entity.response.mappers.toOrderVo
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.profile.GetOrdersUseCase

class GetOrdersUseCaseImpl(
    private val repository: UserRepository
) : GetOrdersUseCase {
    override fun handle() = repository.getOrders()
        .map { list ->
            list.map { response -> response.toOrderVo() }
        }
}