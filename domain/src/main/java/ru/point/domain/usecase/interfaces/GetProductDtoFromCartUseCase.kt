package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ListView

interface GetProductDtoFromCartUseCase {
    fun handle(): Observable<List<ListView>>
}