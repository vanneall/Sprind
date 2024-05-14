package ru.point.repository.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.repository.CartRepository
import ru.point.retrofit.api.CartApi
import javax.inject.Inject

class RemoteCartRepository @Inject constructor(
    private val api: CartApi,
) : CartRepository {

    override fun getProducts(): Observable<List<ProductFeedDto>> {
        return api.getProductsFromCart().subscribeOn(Schedulers.io())
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