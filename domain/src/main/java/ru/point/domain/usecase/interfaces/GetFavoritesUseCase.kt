package ru.point.domain.usecase.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.FeedProductDto

interface GetFavoritesUseCase {
    fun handle(): Observable<List<FeedProductDto>>
}