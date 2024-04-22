package ru.point.repository.retrofitRepository


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.FeedProductDto
import ru.point.domain.entity.ProductDto
import ru.point.domain.repository.ProductRepository
import ru.point.repository.retrofit.ProductApi
import javax.inject.Inject

class RemoteProductRepository @Inject constructor(
    private val api: ProductApi
): ProductRepository {
    override fun getProducts(): Observable<List<FeedProductDto>> {
        return api.getProductDto()
    }

    override fun getProductsByName(name: String): Observable<List<FeedProductDto>> {
        return api.getProductDtoByName(search = name)
    }

    override fun getProductById(id: Long): Single<ProductDto> {
        return api.getProductDtoById(id = id)
    }
}