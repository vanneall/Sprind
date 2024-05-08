package ru.point.repository.remote


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.entity.dto.product.ProductInfoDto
import ru.point.domain.repository.ProductRepository
import ru.point.retrofit.api.ProductApi
import javax.inject.Inject

class RemoteProductRepository @Inject constructor(
    private val api: ProductApi
): ProductRepository {
    override fun getProducts(): Observable<List<ProductFeedDto>> {
        return api.getProductDto()
    }

    override fun getProductsByName(name: String): Observable<List<ProductFeedDto>> {
        return api.getProductDtoByName(search = name)
    }

    override fun getProductById(id: Long): Single<ProductInfoDto> {
        return api.getProductDtoById(id = id)
    }
}