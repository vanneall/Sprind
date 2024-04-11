package ru.point.repository.retrofitRepository


import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.Product
import ru.point.domain.repository.ProductRepository
import ru.point.repository.retrofit.ProductApi
import javax.inject.Inject

class RemoteProductRepository @Inject constructor(
    private val api: ProductApi
): ProductRepository {
    override fun getProducts(): Observable<List<Product>> {
        return api.getProductDto()
    }
}