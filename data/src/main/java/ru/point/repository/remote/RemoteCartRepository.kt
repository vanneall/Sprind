package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.cart.CartPageInfoResponse
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.factory.interfaces.EmptyAddressResponseFactory
import ru.point.domain.repository.CartRepository
import ru.point.repository.paging.CartPagingSource
import ru.point.retrofit.api.CartApi
import ru.point.retrofit.api.FavoriteApi

class RemoteCartRepository(
    private val api: CartApi,
    private val favoriteApi: FavoriteApi,
    private val factory: EmptyAddressResponseFactory
) : CartRepository {
    override fun getPageInfo(): Single<CartPageInfoResponse> {
        return api.getPageInfo()
            .subscribeOn(Schedulers.io())
    }

    override fun getProducts(): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { CartPagingSource(api, favoriteApi, factory) }
        ).observable
            .subscribeOn(Schedulers.io())
    }

    override fun addProduct(id: Long): Completable {
        return api.addProduct(id = id)
            .subscribeOn(Schedulers.io())
    }

    override fun makeOrder(): Completable {
        return api.makeOrder()
            .subscribeOn(Schedulers.io())
    }

    override fun deleteFromCart(id: Long): Completable {
        return api.deleteFromCart(id)
            .subscribeOn(Schedulers.io())
    }
}