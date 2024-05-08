package ru.point.repository.remote

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

    override fun addProduct(id: Long) {
        //TODO переписать на возврат значения с сервера
        api.addProduct(id = id).subscribeOn(Schedulers.io())
            .subscribe({ println("Success") }, { it.printStackTrace() })
    }
}