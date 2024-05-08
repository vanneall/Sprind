package ru.point.sprind.presenter.product.morda

import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.dto.product.ProductFeedDto
import ru.point.domain.usecase.interfaces.cart.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.favorite.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.product.GetProductsUseCase
import ru.point.sprind.entity.deletage.product.feed.ProductDelegate
import javax.inject.Inject

@InjectViewState
class MordaPresenter @Inject constructor(
    private val addProductToCartUseCase: Lazy<AddProductToCartUseCase>,
    private val getProductsUseCase: GetProductsUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<MordaView>() {

    private var delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = addProductToCartUseCase.get()::handle,
            onFavoriteCheckedChange = changeFavoriteStateUseCase.get()::handle
        )
    )
    private var productFeedDtos: List<ProductFeedDto> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.showLoadingScreen()
        val disposable = getProductsUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.disableLoadingScreen()
                productFeedDtos = list

                viewState.setProductAdapter(
                    delegates = delegates, views = productFeedDtos
                )
            }, {
                viewState.disableLoadingScreen()
                viewState.showBadConnectionScreen()
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}