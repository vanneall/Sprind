package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.complex.CartPageInfoDto
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.repository.CartRepository
import ru.point.retrofit.api.CartApi

class RemoteCartRepository(private val api: CartApi) : CartRepository {
    override fun getPageInfo(): Single<CartPageInfoDto> {
        return api.getPageInfo()
            .subscribeOn(Schedulers.io())
    }

    override fun getProducts(offset: Int, limit: Int): Single<List<FeedProductResponse>> {
        return api.getProductsFromCart(offset = offset, limit = limit)
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

//return Pager(
//config = PagingConfig(
//pageSize = 25,
//prefetchDistance = 10,
//maxSize = 45,
//enablePlaceholders = false
//),
//pagingSourceFactory = { cartPagingSource }
//).observable.subscribeOn(Schedulers.io())