package ru.point.repository.remote


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.category.CategoryResponse
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.dto.product.ProductInfoDto
import ru.point.domain.repository.ProductRepository
import ru.point.retrofit.api.CategoryApi
import ru.point.retrofit.api.ProductApi

class RemoteProductRepository(
    private val productApi: ProductApi,
    private val categoriesApi: CategoryApi
) : ProductRepository {

    override fun getMainPageInfo(): Single<ComplexProductDto> {
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

    override fun getProductById(id: Long): Single<ProductInfoDto> {
        return productApi.getProductDtoById(id = id)
            .subscribeOn(Schedulers.io())
    }

    override fun getAvailableCategories(): Single<List<CategoryResponse>> {
        return categoriesApi.getAvailableCategories()
    }
}