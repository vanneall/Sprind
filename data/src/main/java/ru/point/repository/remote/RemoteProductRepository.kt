package ru.point.repository.remote


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.category.CategoryItemResponse
import ru.point.domain.entity.response.complex.ComplexProductResponse
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.entity.response.product.ProductInfoResponse
import ru.point.domain.repository.ProductRepository
import ru.point.retrofit.api.CategoryApi
import ru.point.retrofit.api.ProductApi

class RemoteProductRepository(
    private val productApi: ProductApi,
    private val categoriesApi: CategoryApi
) : ProductRepository {

    override fun getMainPageInfo(): Single<ComplexProductResponse> {
        return productApi.getPageInfo().subscribeOn(Schedulers.io())
    }

    override fun getProductsPaging(
        offset: Int,
        limit: Int,
        request: String?
    ): Single<List<FeedProductResponse>> {
        return productApi.getProductDto(offset = offset, limit = limit, search = request)
            .subscribeOn(Schedulers.io())
    }

    override fun getProductsByName(name: String): Single<List<FeedProductResponse>> {
        return productApi.getProductDto(0, 10, name)
            .subscribeOn(Schedulers.io())
    }

    override fun getProductById(id: Long): Single<ProductInfoResponse> {
        return productApi.getProductDtoById(id = id)
            .subscribeOn(Schedulers.io())
    }

    override fun getAvailableCategories(): Single<List<CategoryItemResponse>> {
        return categoriesApi.getAvailableCategories()
    }
}