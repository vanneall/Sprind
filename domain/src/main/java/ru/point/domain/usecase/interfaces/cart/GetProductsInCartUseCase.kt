package ru.point.domain.usecase.interfaces.cart

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface GetProductsInCartUseCase {
    fun handle(): Observable<List<ViewObject>>
}