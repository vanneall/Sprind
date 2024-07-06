package ru.point.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.retrofit.api.CartApi

class CartPagingSource(
    private val api: CartApi
) : RxPagingSource<Int, ProductFeedDto>() {
    override fun getRefreshKey(state: PagingState<Int, ProductFeedDto>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ProductFeedDto>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return api.getProductsFromCart(startPage, pageSize)
            .map<LoadResult<Int, ProductFeedDto>> { response ->
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