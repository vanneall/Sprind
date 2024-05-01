package ru.point.sprind.presenter.product

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.view.AllCharacteristicsView
import ru.point.domain.entity.view.CharacteristicDescriptionView
import ru.point.domain.entity.view.CharacteristicTitleView
import ru.point.domain.entity.view.ListView
import ru.point.domain.mapper.ProductDtoToListViewMapperImpl
import ru.point.domain.usecase.interfaces.GetProductByIdUseCase
import ru.point.sprind.entity.deletage.product.card.AllCharacteristicsDelegate
import ru.point.sprind.entity.deletage.product.card.CharacteristicDelegate
import ru.point.sprind.entity.deletage.product.card.CharacteristicTitleDelegate
import ru.point.sprind.entity.deletage.product.card.ProductDescriptionDelegate
import ru.point.sprind.entity.deletage.product.card.ProductImageDelegate
import ru.point.sprind.entity.deletage.product.card.ProductTitleDelegate
import ru.point.sprind.presenter.product.ProductPresenterAssistedFactory.Companion.ID

@InjectViewState
class ProductPresenter @AssistedInject constructor(
    @Assisted(ID) private val productId: Long,
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : MvpPresenter<ProductCardView>() {

    val delegates = listOf(
        ProductImageDelegate(),
        ProductTitleDelegate(),
        ProductDescriptionDelegate(),
        AllCharacteristicsDelegate(::expandCharacteristics),
        CharacteristicDelegate(),
        CharacteristicTitleDelegate()
    )
    private var productListView: List<ListView> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    fun getProduct() {

        val disposable = getProductByIdUseCase
            .invoke(id = productId, ProductDtoToListViewMapperImpl())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                productListView = list
                viewState.disableLoadingScreen()

                viewState.setProductAdapter(productListView, delegates)
            }, {
                println(it.stackTraceToString())
                viewState.disableLoadingScreen()
                viewState.showBadConnectionScreen()
            }
            )

        compositeDisposable.add(disposable)
    }

    private fun expandCharacteristics(isExpanded: Boolean) {
        val startIndex = productListView.indexOfFirst { view -> view is AllCharacteristicsView }

        if (startIndex == -1) return
        val characteristic = productListView[startIndex] as? AllCharacteristicsView ?: return

        if (isExpanded) {
            val nel = characteristic.run {
                characteristics.map { characteristic ->
                    listOf(
                        CharacteristicTitleView(characteristic.name)
                    ) + characteristic.elements.map { pair ->
                        CharacteristicDescriptionView(pair.first, pair.second)
                    }
                }.flatten()
            }

            productListView = buildExpandedList(startIndex, nel)
        } else {
            val endIndex =
                productListView.indexOfLast { view -> view is CharacteristicDescriptionView }
            productListView = buildNotExpandedList(startIndex, endIndex)
        }



        characteristic.let {
            viewState
                .setProductAdapter(
                    list = productListView,
                    delegates = delegates
                )
        }
    }

    private fun buildExpandedList(
        indexForInsert: Int,
        characteristics: List<ListView>,
    ): List<ListView> {
        return productListView.subList(
            0,
            indexForInsert + 1
        ) + characteristics + productListView.subList(indexForInsert + 1, productListView.size)
    }

    private fun buildNotExpandedList(
        indexForInsert: Int,
        indexOfEnd: Int,
    ): List<ListView> {
        return productListView.subList(
            0,
            indexForInsert + 1
        ) + productListView.subList(indexOfEnd + 1, productListView.size)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}