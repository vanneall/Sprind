package ru.point.repository.remote


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.ProductInfoDto
import ru.point.domain.repository.ProductRepository
import ru.point.retrofit.api.ProductApi
import javax.inject.Inject

class RemoteProductRepository @Inject constructor(
    private val api: ProductApi
): ProductRepository {
    override fun getProducts(): Observable<ComplexProductDto> {
        return api.getProductDto().subscribeOn(Schedulers.io())
    }

    override fun getProductsByName(name: String): Observable<ComplexProductDto> {
        return api.getProductDtoByName(search = name).subscribeOn(Schedulers.io())
    }

    override fun getProductById(id: Long): Single<ProductInfoDto> {
        return api.getProductDtoById(id = id).subscribeOn(Schedulers.io())
    }
}