package ru.point.domain.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.product.FeedProductResponse
import ru.point.domain.repository.FavoriteRepository

class FavoritePagingSource(
    private val repository: FavoriteRepository
) : RxPagingSource<Int, FeedProductResponse>() {
    override fun getRefreshKey(state: PagingState<Int, FeedProductResponse>): Int? = null
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FeedProductResponse>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return repository.getFavorite(startPage, pageSize)
            .map<LoadResult<Int, FeedProductResponse>> { response ->
                val nextKey = if (response.size < pageSize) null else pageSize
                val prevKey = if (startPage == 0) null else startPage - pageSize

                LoadResult.Page(
                    data = response,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable ->
                LoadResult.Error(throwable)
            }
    }
}