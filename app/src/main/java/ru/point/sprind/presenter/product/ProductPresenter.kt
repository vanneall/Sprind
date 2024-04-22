package ru.point.sprind.presenter.product

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.ListView
import ru.point.domain.usecase.implementation.ProductDtoToListViewMapperImpl
import ru.point.domain.usecase.interfaces.GetProductByIdUseCase
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.product.card.ProductDescriptionDelegate
import ru.point.sprind.entity.deletage.product.card.ProductImageDelegate
import ru.point.sprind.entity.deletage.product.card.ProductTitleDelegate
import javax.inject.Inject

@InjectViewState
class ProductPresenter @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : MvpPresenter<ProductCardView>() {
    private var delegates: List<Delegate> = emptyList()
    private var productListView: List<ListView> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    fun init(id: Long) {
        delegates = listOf(
            ProductImageDelegate(),
            ProductTitleDelegate(),
            ProductDescriptionDelegate()
        )

        val disposable = getProductByIdUseCase.invoke(id = id, ProductDtoToListViewMapperImpl())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                productListView = list
                viewState.disableLoadingScreen()
                if (list.isEmpty()) {
                    viewState.setNotFound()
                } else {
                    viewState.setProductAdapter(productListView, delegates)
                }
            }, {
                viewState.disableLoadingScreen()
                viewState.setBadConnection()
            }
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}