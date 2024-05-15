package ru.point.domain.usecase.interfaces.cart

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.complex.ComplexProductCartVoContainer

interface GetProductsInCartUseCase {
    fun handle(): Observable<ComplexProductCartVoContainer>
}