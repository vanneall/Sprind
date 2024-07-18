package ru.point.domain.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toCategoryVo
import ru.point.domain.entity.response.mappers.toProductFeedVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.repository.ProductRepository

class FeedPagingSource(
    private val repository: ProductRepository
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return repository.getProductsPaging(startPage, pageSize, null)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else pageSize

                var categories = listOf<ViewObject>()
                if (prevKey == null) {
                    categories = listOf(
                        NestedRecyclerViewVo(
                            viewObjects = repository.getAvailableCategories()
                                .blockingGet()
                                .map { categoryResponse -> categoryResponse.toCategoryVo() })
                    )
                }

                val resultPageData = categories +
                        response.map { productResponse ->
                            productResponse.toProductFeedVo()
                        }

                LoadResult.Page(
                    data = resultPageData,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { throwable ->
                LoadResult.Error(throwable)
            }
    }
}