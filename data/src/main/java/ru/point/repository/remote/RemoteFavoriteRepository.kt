package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.repository.FavoriteRepository
import ru.point.retrofit.api.FavoriteApi

class RemoteFavoriteRepository(private val api: FavoriteApi) : FavoriteRepository {
    override fun getFavorite(offset: Int, limit: Int): Single<List<FeedProductResponse>> {
        return api.getFavorites(offset = offset, limit = limit)
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