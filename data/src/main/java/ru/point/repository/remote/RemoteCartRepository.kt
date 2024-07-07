package ru.point.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.complex.CartPageInfoDto
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.repository.CartRepository
import ru.point.repository.paging.CartPagingSource
import ru.point.retrofit.api.CartApi

class RemoteCartRepository(
    private val api: CartApi,
    private val cartPagingSource: CartPagingSource
) : CartRepository {

    override fun getPageInfo(): Single<CartPageInfoDto> {
        return api.getPageInfo().subscribeOn(Schedulers.io())
    }

    override fun getProducts(): Observable<PagingData<ResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                maxSize = 45,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { cartPagingSource }
        ).observable.subscribeOn(Schedulers.io())
    }

    override fun addProduct(id: Long): Completable {
        return api.addProduct(id = id).subscribeOn(Schedulers.io())
    }

    override fun makeOrder(): Completable {
        return api.makeOrder().subscribeOn(Schedulers.io())
    }

    override fun deleteFromCart(id: Long): Completable {
        return api.deleteFromCart(id).subscribeOn(Schedulers.io())
    }
}