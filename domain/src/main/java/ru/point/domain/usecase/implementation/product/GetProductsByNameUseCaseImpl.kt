package ru.point.domain.usecase.implementation.product

import androidx.paging.PagingData
import androidx.paging.map
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.toProductFeedVo
import ru.point.domain.entity.view.product.card.FeedProductVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductsByNameUseCase

class GetProductsByNameUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductsByNameUseCase {

    override fun handle(search: String): Observable<PagingData<FeedProductVo>> {
        return repository.getProductsByName(name = search)
            .observeOn(Schedulers.computation())
            .map { pagingData ->
                pagingData.map { response ->
                    response.toProductFeedVo()
                }
            }
    }
}