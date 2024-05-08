package ru.point.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface FavoriteRepository {
    fun getFavorite(): Observable<List<ProductFeedDto>>

    fun addFavorite(id: Long)

    fun deleteFavorite(id: Long)

}