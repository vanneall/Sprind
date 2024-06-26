package ru.point.domain.usecase.interfaces.product

import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.view.ViewObject

interface GetProductByIdUseCase {
    fun invoke(id: Long): Single<List<ViewObject>>
}