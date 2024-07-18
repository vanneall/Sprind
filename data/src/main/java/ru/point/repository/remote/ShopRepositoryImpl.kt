package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.ShopRepository
import ru.point.repository.paging.ShopPagingSource
import ru.point.retrofit.api.ShopApi

class ShopRepositoryImpl(
    private val api: ShopApi
) : ShopRepository {
    override fun getShopProducts(shopId: Long): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { ShopPagingSource(shopId, api) }
        ).observable
            .subscribeOn(Schedulers.io())
    }
}