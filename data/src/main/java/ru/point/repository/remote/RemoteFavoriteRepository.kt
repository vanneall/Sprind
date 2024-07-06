package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.repository.FavoriteRepository
import ru.point.repository.paging.FavoritePagingSource
import ru.point.retrofit.api.FavoriteApi
import javax.inject.Inject

class RemoteFavoriteRepository @Inject constructor(
    private val api: FavoriteApi,
    private val favoritePagingSource: FavoritePagingSource
) : FavoriteRepository {
    override fun getFavorite(): Observable<PagingData<ProductFeedDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                maxSize = 45,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { favoritePagingSource }
        ).observable
    }

    override fun addFavorite(id: Long): Completable {
        return api.putFavorites(id = id).subscribeOn(Schedulers.io())
    }

    override fun deleteFavorite(id: Long): Completable {
        return api.deleteFavorites(id = id).subscribeOn(Schedulers.io())
    }
}