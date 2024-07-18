package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.FavoriteRepository
import ru.point.repository.paging.FavoritePagingSource
import ru.point.retrofit.api.FavoriteApi

class RemoteFavoriteRepository(private val api: FavoriteApi) : FavoriteRepository {

    override fun getFavorite(): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { FavoritePagingSource(api) }
        ).observable
            .subscribeOn(Schedulers.io())
    }

    override fun addFavorite(id: Long): Completable {
        return api.putFavorites(id = id)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteFavorite(id: Long): Completable {
        return api.deleteFavorites(id = id)
            .subscribeOn(Schedulers.io())
    }
}