package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.Product

interface GetProductsByNameUseCase {
    fun handle(search: String): Observable<List<Product>>
}