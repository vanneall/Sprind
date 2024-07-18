package ru.point.domain.usecase.implementation.profile

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toOrderVo
import ru.point.domain.entity.view.order.OrderVo
import ru.point.domain.repository.UserRepository
import ru.point.domain.usecase.interfaces.profile.GetOrdersUseCase

class GetOrdersUseCaseImpl(
    private val repository: UserRepository
) : GetOrdersUseCase {

    override fun handle(): Single<List<OrderVo>> {
        return repository.getOrders().map { list -> list.map { dto -> dto.toOrderVo() } }
    }
}