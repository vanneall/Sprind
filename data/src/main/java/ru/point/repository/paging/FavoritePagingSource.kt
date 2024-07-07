package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.retrofit.api.FavoriteApi

class FavoritePagingSource(
    private val api: FavoriteApi
) : RxPagingSource<Int, FeedProductResponse>() {
    override fun getRefreshKey(state: PagingState<Int, FeedProductResponse>): Int? = null
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FeedProductResponse>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return api.getFavorites(startPage, pageSize)
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