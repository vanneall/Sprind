package ru.point.sprind.presenter.product

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
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
import ru.point.sprind.presenter.product.ProductPresenterAssistedFactory.Companion.ID

@InjectViewState
class ProductPresenter @AssistedInject constructor(
    @Assisted(ID) private val productId: Long,
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : MvpPresenter<ProductCardView>() {

    private var delegates: List<Delegate> = emptyList()
    private var productListView: List<ListView> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    fun getProduct() {

        delegates = listOf(
            ProductImageDelegate(),
            ProductTitleDelegate(),
            ProductDescriptionDelegate()
        )

        val disposable = getProductByIdUseCase
            .invoke(id = productId, ProductDtoToListViewMapperImpl())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                productListView = list
                viewState.disableLoadingScreen()

                viewState.setProductAdapter(productListView, delegates)
            }, {
                viewState.disableLoadingScreen()
                viewState.showBadConnectionScreen()
            }
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}