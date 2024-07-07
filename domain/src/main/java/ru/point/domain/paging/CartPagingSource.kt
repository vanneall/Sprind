package ru.point.domain.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.utils.AddressInfoResponse
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.repository.CartRepository

class CartPagingSource(
    private val repository: CartRepository
) : RxPagingSource<Int, ResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseItem>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ResponseItem>> {
        val startPage = params.key ?: 0
        val pageSize = params.loadSize

        return repository.getProducts(startPage, pageSize)
            .map<LoadResult<Int, ResponseItem>> { response ->
                val prevKey = if (startPage == 0) null else startPage - pageSize
                val nextKey = if (response.size < pageSize) null else pageSize

                val headerItems = mutableListOf<ResponseItem>()
                val footerItems = mutableListOf<ResponseItem>()
                if (prevKey == null || nextKey == null) {
                    getPageInfoResponse(
                        isAddressRequired = prevKey == null,
                        isOrderSummaryRequired = nextKey == null,
                        addressItemContainer = headerItems,
                        summaryItemContainer = footerItems
                    )
                }

                val resultPageData = headerItems + response + footerItems

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

    private fun getPageInfoResponse(
        isAddressRequired: Boolean,
        isOrderSummaryRequired: Boolean,
        addressItemContainer: MutableList<ResponseItem>,
        summaryItemContainer: MutableList<ResponseItem>
    ) {
        val pageInfoResponse = repository.getPageInfo()
            .subscribeOn(Schedulers.io())
            .blockingGet()

        if (isAddressRequired && pageInfoResponse.summaryPriceResponse != null)
            addressItemContainer.add(pageInfoResponse.addressInfoResponse ?: AddressInfoResponse(null, null, null, null))
        if (isOrderSummaryRequired)
            summaryItemContainer.addNotNull(pageInfoResponse.summaryPriceResponse)
    }
}

fun <T> MutableList<T>.addNotNull(element: T?) {
    if (element != null) add(element)
}