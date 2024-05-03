package ru.point.repository.retrofitRepository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.repository.FavoriteRepository
import ru.point.repository.retrofit.FavoriteApi
import javax.inject.Inject

class RemoteFavoriteRepository @Inject constructor(
    private val api: FavoriteApi,
) : FavoriteRepository {
    override fun getFavorite(): Observable<List<FeedProductDto>> {
        return api.getFavorites().subscribeOn(Schedulers.io())
    }

    override fun addFavorite(id: Long) {
        api.putFavorites(id = id).subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
    }

    override fun deleteFavorite(id: Long) {
        api.deleteFavorites(id = id).subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
    }
}