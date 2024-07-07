package ru.point.domain.usecase.implementation.cart

import androidx.paging.PagingData
import androidx.paging.map
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.complex.SummaryPriceInfoResponse
import ru.point.domain.entity.dto.complex.toCartSummaryVo
import ru.point.domain.entity.dto.product.FeedProductResponse
import ru.point.domain.entity.dto.product.toCartProductVo
import ru.point.domain.entity.utils.AddressInfoResponse
import ru.point.domain.entity.utils.ResponseItem
import ru.point.domain.entity.utils.toAddressVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.domain.exceptions.ViewObjectMapRuleNotFoundException
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase

class GetProductsInCartUseCaseImpl(
    private val repository: CartRepository,
) : GetProductsInCartUseCase {
    override fun handle(): Observable<PagingData<ViewObject>> {
        return repository.getProducts()
            .observeOn(Schedulers.computation())
            .map { pagingData ->
                pagingData.map { item -> mapResponseItem(item) }
            }
    }

    private fun mapResponseItem(responseItem: ResponseItem): ViewObject {
        return when (responseItem) {
            is AddressInfoResponse -> CartHeaderVo(responseItem.toAddressVo())
            is FeedProductResponse -> responseItem.toCartProductVo()
            is SummaryPriceInfoResponse -> responseItem.toCartSummaryVo()
            else -> throw ViewObjectMapRuleNotFoundException("Mapper for $responseItem not found")
        }
    }
}