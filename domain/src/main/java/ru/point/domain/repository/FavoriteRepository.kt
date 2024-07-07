package ru.point.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.FeedProductResponse

interface FavoriteRepository {
    fun getFavorite(offset: Int, limit: Int): Single<List<FeedProductResponse>>

    fun addFavorite(id: Long): Completable

    fun deleteFavorite(id: Long): Completable

}