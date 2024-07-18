package ru.point.domain.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.response.mappers.toProductFeedVo
import ru.point.domain.entity.response.mappers.toShopVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.paging.CategoryPagingSource.Factory.Companion.ID
import ru.point.domain.repository.CategoryRepository

class CategoryPagingSource @AssistedInject constructor(
    @Assisted(ID)
    private val categoryId: Long,
    private val repository: CategoryRepository
) : RxPagingSource<Int, ViewObject>() {
    override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return repository.getProductsByCategoryId(startPage, pageSize, categoryId)
            .map<LoadResult<Int, ViewObject>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else pageSize

                var shops = listOf<ViewObject>()
                if (prevKey == null) {
                    val shop = repository
                        .getCategoryInfo(categoryId = categoryId)
                        .blockingGet().shops

                    shops = listOf(
                        NestedRecyclerViewVo(
                            viewObjects = shop.map { shopResponse ->
                                shopResponse.toShopVo()
                            }
                        )
                    )
                }

                val resultPageData = shops + response.map { it.toProductFeedVo() }

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

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(ID) categoryId: Long): CategoryPagingSource

        companion object {
            const val ID = "ru.point.domain.paging.ID"
        }
    }
}