package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.dto.product.ProductFeedDto

interface FavoriteRepository {
    fun getFavorite(): Observable<PagingData<ProductFeedDto>>

    fun addFavorite(id: Long): Completable

    fun deleteFavorite(id: Long): Completable

}