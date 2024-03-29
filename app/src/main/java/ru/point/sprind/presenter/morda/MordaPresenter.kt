package ru.point.sprind.presenter.morda

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.Product
import ru.point.domain.usecase.interfaces.GetProductsUseCase
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.usecase.GetMordaDelegatesUseCase
import javax.inject.Inject

@InjectViewState
class MordaPresenter @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    getMordaDelegatesUseCase: GetMordaDelegatesUseCase
) : MvpPresenter<MordaView>() {

    val delegates: List<Delegate>
    val products: List<Product>

    init {
        delegates = getMordaDelegatesUseCase.handle()
        products = getProductsUseCase.handle()
    }
}