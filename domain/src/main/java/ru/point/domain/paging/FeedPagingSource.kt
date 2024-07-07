package ru.point.domain.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.paging.FeedPagingSource.Factory.Companion.REQUEST
import ru.point.domain.repository.ProductRepository

class FeedPagingSource @AssistedInject constructor(
    private val repository: ProductRepository,
    @Assisted(REQUEST) private val request: String?
) : RxPagingSource<Int, FeedProductResponse>() {
    override fun getRefreshKey(state: PagingState<Int, FeedProductResponse>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FeedProductResponse>> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return repository.getProductsPaging(page, pageSize, request)
            .map<LoadResult<Int, FeedProductResponse>> { products ->
                val nextKey = if (products.size < pageSize) null else page + 1
                val prevKey = if (page == 0) null else page - 1

                LoadResult.Page(
                    data = products,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable ->
                LoadResult.Error(throwable)
            }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(REQUEST) request: String?): FeedPagingSource

        companion object {
            const val REQUEST = "REQUEST"
        }
    }
}