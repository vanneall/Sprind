package ru.point.repository.remote


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.complex.ComplexProductDto
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.dto.product.ProductInfoDto
import ru.point.domain.repository.ProductRepository
import ru.point.repository.paging.FeedPagingSource
import ru.point.retrofit.api.ProductApi

class RemoteProductRepository(
    private val api: ProductApi,
    private val pagingSourceFactory: FeedPagingSource.Factory
) : ProductRepository {

    override fun getMainPageInfo(): Single<ComplexProductDto> {
        return api.getPageInfo().subscribeOn(Schedulers.io())
    }

    override fun getProductsPaging(): Observable<PagingData<FeedProductResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                maxSize = 45,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSourceFactory.create(null) }
        ).observable
    }

    override fun getProductsByName(name: String): Observable<PagingData<FeedProductResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 10,
                maxSize = 45,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSourceFactory.create(name) }
        ).observable
    }

    override fun getProductById(id: Long): Single<ProductInfoDto> {
        return api.getProductDtoById(id = id).subscribeOn(Schedulers.io())
    }
}