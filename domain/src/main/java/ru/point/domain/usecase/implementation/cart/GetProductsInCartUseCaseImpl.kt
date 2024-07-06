package ru.point.domain.usecase.implementation.cart

import androidx.paging.PagingData
import androidx.paging.map
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.dto.product.toCartProductVo
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.repository.CartRepository
import ru.point.domain.usecase.interfaces.cart.GetProductsInCartUseCase
import javax.inject.Inject

class GetProductsInCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : GetProductsInCartUseCase {
    override fun handle(): Observable<PagingData<ViewObject>> {
        return repository.getProducts()
            .observeOn(Schedulers.computation())
            .map { pagingData ->
                pagingData.map { response ->
                    response.toCartProductVo()
                }
            }
    }
}