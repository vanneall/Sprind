package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.repository.FavoriteRepository
import ru.point.retrofit.api.FavoriteApi
import javax.inject.Inject

class RemoteFavoriteRepository @Inject constructor(
    private val api: FavoriteApi,
) : FavoriteRepository {
    override fun getFavorite(): Observable<List<ProductFeedDto>> {
        return api.getFavorites().subscribeOn(Schedulers.io())
    }

    override fun addFavorite(id: Long): Completable {
        return api.putFavorites(id = id).subscribeOn(Schedulers.io())
    }

    override fun deleteFavorite(id: Long): Completable {
        return api.deleteFavorites(id = id).subscribeOn(Schedulers.io())
    }
}