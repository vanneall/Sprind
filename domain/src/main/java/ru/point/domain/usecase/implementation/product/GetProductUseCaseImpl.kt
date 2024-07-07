package ru.point.domain.usecase.implementation.product

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.point.domain.entity.dto.product.toProductFeedVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsUseCase {
    override fun handle(): RxPagingSource<Int, ViewObject> {
        return object : RxPagingSource<Int, ViewObject>() {
            override fun getRefreshKey(state: PagingState<Int, ViewObject>): Int? = null

            override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ViewObject>> {
                val startPage = params.key ?: 0
                val pageSize = params.loadSize

                return repository.getProductsPaging(startPage, pageSize, null)
                    .map<LoadResult<Int, ViewObject>> { response ->
                        val prevKey = if (startPage == 0) null else startPage - pageSize
                        val nextKey = if (response.size < pageSize) null else pageSize

                        val resultPageData = response.map { it.toProductFeedVo() }

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
    }
}