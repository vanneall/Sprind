package ru.point.domain.usecase.interfaces.profile

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.view.order.OrderVo

interface GetOrdersUseCase {
    fun handle(): Single<List<OrderVo>>
}