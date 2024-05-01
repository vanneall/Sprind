package ru.point.repository.retrofitRepository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.repository.CartRepository
import ru.point.repository.retrofit.CartApi
import javax.inject.Inject

class RemoteCartRepository @Inject constructor(
    private val api: CartApi,
) : CartRepository {

    override fun getProducts(): Observable<List<FeedProductDto>> {
        return api.getProductsFromCart().subscribeOn(Schedulers.io())
    }
}