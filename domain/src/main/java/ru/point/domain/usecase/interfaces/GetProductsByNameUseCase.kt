package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.FeedProductDto

interface GetProductsByNameUseCase {
    fun handle(search: String): Observable<List<FeedProductDto>>
}