package ru.point.sprind.presenter.morda

import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.usecase.interfaces.AddProductToCartUseCase
import ru.point.domain.usecase.interfaces.ChangeFavoriteStateUseCase
import ru.point.domain.usecase.interfaces.GetProductsUseCase
import ru.point.sprind.entity.deletage.ProductDelegate
import javax.inject.Inject

@InjectViewState
class MordaPresenter @Inject constructor(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val changeFavoriteStateUseCase: Lazy<ChangeFavoriteStateUseCase>,
) : MvpPresenter<MordaView>() {

    private var delegates = listOf(
        ProductDelegate(
            onClickCard = viewState::openCard,
            onBuyClick = addProductToCartUseCase::handle,
            onFavoriteCheckedChange = changeFavoriteStateUseCase.get()::handle
        )
    )
    private var feedProductDtos: List<FeedProductDto> = emptyList()

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState.showLoadingScreen()
        val disposable = getProductsUseCase.handle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                viewState.disableLoadingScreen()
                feedProductDtos = list

                viewState.setProductAdapter(
                    delegates = delegates, views = feedProductDtos
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