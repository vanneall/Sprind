package ru.point.repository.remote


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.dto.product.ProductInfoDto
import ru.point.domain.repository.ProductRepository
import ru.point.retrofit.api.ProductApi

class RemoteProductRepository(private val api: ProductApi) : ProductRepository {

    override fun getMainPageInfo(): Single<ComplexProductDto> {
        return api.getPageInfo().subscribeOn(Schedulers.io())
    }

    override fun getProductsPaging(
        offset: Int,
        limit: Int,
        request: String?
    ): Single<List<FeedProductResponse>> {
        return api.getProductDto(offset = offset, limit = limit, search = request)
            .subscribeOn(Schedulers.io())
    }

    override fun getProductsByName(name: String): Single<List<FeedProductResponse>> {
        return api.getProductDto(0, 10, name)
            .subscribeOn(Schedulers.io())
    }

    override fun getProductById(id: Long): Single<ProductInfoDto> {
        return api.getProductDtoById(id = id)
            .subscribeOn(Schedulers.io())
    }
}