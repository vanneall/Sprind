package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.FeedProductDto

interface FavoriteRepository {
    fun getFavorite(): Observable<List<FeedProductDto>>
}