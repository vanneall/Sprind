package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.repository.paging.FeedPagingSource.Factory.Companion.REQUEST
import ru.point.retrofit.api.ProductApi

class FeedPagingSource @AssistedInject constructor(
    private val api: ProductApi,
    @Assisted(REQUEST) private val request: String?
) : RxPagingSource<Int, ProductFeedDto>() {
    override fun getRefreshKey(state: PagingState<Int, ProductFeedDto>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ProductFeedDto>> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return api.getProductDto(page, pageSize, request)
            .map<LoadResult<Int, ProductFeedDto>> { products ->
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