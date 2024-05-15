package ru.point.domain.usecase.interfaces.product

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.complex.ComplexProductFeedVoContainer

interface GetProductsUseCase {
    fun handle(): Observable<ComplexProductFeedVoContainer>
}