package ru.point.repository.remote


import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.response.category.CategoryItemResponse
import ru.point.domain.entity.response.complex.ComplexProductResponse
import ru.point.domain.entity.response.product.ProductInfoResponse
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.ProductRepository
import ru.point.repository.paging.FeedPagingSource
import ru.point.repository.paging.NamedProductsPagingSource
import ru.point.retrofit.api.CategoryApi
import ru.point.retrofit.api.ProductApi

class RemoteProductRepository(
    private val productApi: ProductApi,
    private val categoriesApi: CategoryApi
) : ProductRepository {

    override fun getMainPageInfo(): Single<ComplexProductResponse> {
        return productApi.getPageInfo().subscribeOn(Schedulers.io())
    }

    override fun getProductsPaging(): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { FeedPagingSource(productApi, categoriesApi) }
        ).observable
            .subscribeOn(Schedulers.io())
    }

    override fun getProductsByName(name: String): Observable<PagingData<ViewObject>> {
        return Pager(
            config = pagerConfig,
            pagingSourceFactory = { NamedProductsPagingSource(name, productApi) }
        ).observable
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