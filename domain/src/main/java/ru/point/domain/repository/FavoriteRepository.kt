package ru.point.domain.repository

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.view.ViewObject

interface FavoriteRepository {
    fun getFavorite(): Observable<PagingData<ViewObject>>

    fun addFavorite(id: Long): Completable

    fun deleteFavorite(id: Long): Completable

}