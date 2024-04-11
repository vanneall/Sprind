package ru.point.data

import io.reactivex.rxjava3.core.Observable
import ru.point.domain.entity.Product
import ru.point.domain.repository.ProductRepository
import javax.inject.Inject


class FakeRepository @Inject constructor() : ProductRepository {
    override fun getProducts(): Observable<List<Product>> {
        return Observable.empty()
    }

    override fun getProductsByName(name: String): Observable<List<Product>> {
        TODO("Not yet implemented")
    }
}